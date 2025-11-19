package com.gromber05.act3t3.domain

import com.gromber05.act3t3.domainP.Producto
import jakarta.persistence.*

@Entity
@Table(name = "categorias")
data class Categoria(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val nombre: String,

    val descripcion: String? = null,

    @OneToMany(mappedBy = "categoria", cascade = [CascadeType.ALL], orphanRemoval = true)
    val productos: MutableList<Producto> = mutableListOf()
)
