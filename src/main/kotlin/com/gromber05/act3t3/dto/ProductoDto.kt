package com.gromber05.act3t3.dto

import java.math.BigDecimal

data class ProductoDto(
    val id: Long?,
    val nombre: String,
    val precio: BigDecimal,
    val stock: Int,
    val categoriaId: Long
)
