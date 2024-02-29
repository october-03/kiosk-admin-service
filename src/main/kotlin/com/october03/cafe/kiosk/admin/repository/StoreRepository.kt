package com.october03.cafe.kiosk.admin.repository

import com.october03.cafe.kiosk.admin.dto.StoreRole
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

@Entity
@Table(name = "store")
data class Store (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
  val name: String,
  val latitude: Double,
  val longitude: Double,
  @Enumerated(EnumType.STRING)
  val role: StoreRole,
  val password: String,
  val createdAt: LocalDateTime = LocalDateTime.now(),
) {
  constructor() : this(
    null,
    "",
    0.0,
    0.0,
    StoreRole.ROLE_STORE,
    "",
  )
}

interface StoreRepository: JpaRepository<Store, Long> {
  fun findByRole(role: StoreRole): List<Store>
}