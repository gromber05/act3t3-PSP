package com.gromber05.act3t3.dto

data class CategoriaConProductosDto(
    val id: Long?,
    val nombre: String,
    val descripcion: String?,
    val productos: List<ProductoDto>
)
