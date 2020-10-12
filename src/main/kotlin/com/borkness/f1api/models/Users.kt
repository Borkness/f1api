package com.borkness.f1api.models

import javax.persistence.*

@Entity
@Table(name = "users")
data class Users (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        @Column(nullable = false)
        val username: String,
        @Column(nullable = false)
        var password: String,
        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinTable(
                name = "user_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
        )
        var roles: Set<Roles>? = null
)