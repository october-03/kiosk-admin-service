package com.october03.cafe.kiosk.admin.dto

import com.october03.cafe.kiosk.admin.repository.Store

data class LoginResponse(
  val user: Store,
  val accessToken: String
)
