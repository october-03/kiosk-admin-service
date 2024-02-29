package com.october03.cafe.kiosk.admin.dto

data class CreateMemberDto(
  val name: String,
  val latitude: Double,
  val longitude: Double,
  val password: String,
)
