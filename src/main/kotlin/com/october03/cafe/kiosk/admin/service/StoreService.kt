package com.october03.cafe.kiosk.admin.service

import com.october03.cafe.kiosk.admin.dto.CreateMemberDto
import com.october03.cafe.kiosk.admin.dto.StoreRole
import com.october03.cafe.kiosk.admin.repository.Store
import com.october03.cafe.kiosk.admin.repository.StoreRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class StoreService(
  private val storeRepository: StoreRepository
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
}