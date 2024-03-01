package com.october03.cafe.kiosk.admin.service

import com.october03.cafe.kiosk.admin.repository.Store
import com.october03.cafe.kiosk.admin.repository.StoreRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserService(
  private val storeRepository: StoreRepository
): UserDetailsService {
  override fun loadUserByUsername(userName: String): UserDetails =
    storeRepository.findById(userName.toLong())
      .map { createUserDetails(it) }
      .orElseThrow { UsernameNotFoundException("해당 유저는 없습니다.") }

  private fun createUserDetails(user: Store): UserDetails =
    CustomUser(
      user.id!!,
      user.id.toString(),
      user.password,
      listOf(SimpleGrantedAuthority(user.role.name))
    )
}

class CustomUser(
  val userId: Long,
  userName: String,
  password: String,
  authorities: Collection<GrantedAuthority>
): User(userName, password, authorities)