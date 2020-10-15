package com.borkness.f1api.models

import javax.persistence.*

@Entity
@Table(name = "user_roles")
data class UserRoles(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        @Column(name = "user_id")
        val userId: Long,
        @Column(name = "role_id")
        val roleId: Int
)