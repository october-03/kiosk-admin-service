package com.october03.cafe.kiosk.admin.service

import com.october03.cafe.kiosk.admin.config.TokenProvider
import com.october03.cafe.kiosk.admin.dto.CreateMemberDto
import com.october03.cafe.kiosk.admin.dto.LoginDto
import com.october03.cafe.kiosk.admin.dto.LoginResponse
import com.october03.cafe.kiosk.admin.dto.StoreRole
import com.october03.cafe.kiosk.admin.repository.Store
import com.october03.cafe.kiosk.admin.repository.StoreRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class StoreService(
  private val storeRepository: StoreRepository,
  private val tokenProvider: TokenProvider,
  private val authenticationManagerBuilder: AuthenticationManagerBuilder
) {
  fun createStore(req: CreateMemberDto): Store {
    val hashedPassword = BCryptPasswordEncoder().encode(req.password)

    val newAdmin = Store(
      name = req.name,
      latitude = req.latitude,
      longitude = req.longitude,
      password = hashedPassword,
      role = StoreRole.ROLE_STORE
    )

    storeRepository.save(newAdmin)
    return newAdmin
  }

  fun getStoreList(): List<Store> {
    return storeRepository.findByRole(StoreRole.ROLE_STORE)
  }

  fun login(req: LoginDto): LoginResponse? {
    val store = storeRepository.findById(req.id).get()
    val passwordMatches = BCryptPasswordEncoder().matches(req.password, store.password)
    if (passwordMatches) {
      try {
        val authenticationToken = UsernamePasswordAuthenticationToken(req.id, req.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        val accessToken = tokenProvider.createToken(authentication)

        val result = LoginResponse(store, accessToken)

        return result
      } catch (e: Exception) {
        println("Login Exception: $e")
      }
    } else {
      println("Password does not match")
    }
    return null
  }
}