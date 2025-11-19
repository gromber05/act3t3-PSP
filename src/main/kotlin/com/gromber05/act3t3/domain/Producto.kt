package com.gromber05.act3t3.domainP

import com.gromber05.act3t3.domain.Categoria
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "productos")
data class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false)
    val precio: BigDecimal,

    @Column(nullable = false)
    val stock: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    val categoria: Categoria
)
