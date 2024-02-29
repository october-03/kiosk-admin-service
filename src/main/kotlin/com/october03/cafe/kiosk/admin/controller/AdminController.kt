package com.october03.cafe.kiosk.admin.controller

import com.october03.cafe.kiosk.admin.dto.CreateMemberDto
import com.october03.cafe.kiosk.admin.repository.Store
import com.october03.cafe.kiosk.admin.service.AdminService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController(
  private val adminService: AdminService
) {
  @PostMapping("/register/admin")
  fun registerAdmin(@RequestBody req: CreateMemberDto): Store {
    return adminService.createAdmin(req)
  }
}