package com.october03.cafe.kiosk.admin.controller

import com.october03.cafe.kiosk.admin.dto.CreateMemberDto
import com.october03.cafe.kiosk.admin.repository.Store
import com.october03.cafe.kiosk.admin.service.StoreService
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
}