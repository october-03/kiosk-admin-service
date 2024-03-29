package com.october03.cafe.kiosk.admin.controller

import com.october03.cafe.kiosk.admin.dto.CreateMemberDto
import com.october03.cafe.kiosk.admin.dto.LoginDto
import com.october03.cafe.kiosk.admin.dto.LoginResponse
import com.october03.cafe.kiosk.admin.repository.Store
import com.october03.cafe.kiosk.admin.service.StoreService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StoreController(
  private val storeService: StoreService
) {
  @PostMapping("/register/store")
  fun registerStore(@RequestBody req: CreateMemberDto): Store {
    return storeService.createStore(req)
  }

  @GetMapping("/store")
  fun getStoreList(): List<Store> {
    return storeService.getStoreList()
  }

  @PostMapping("/login")
  fun login(@RequestBody req: LoginDto): LoginResponse? {
    return storeService.login(req)
  }
}