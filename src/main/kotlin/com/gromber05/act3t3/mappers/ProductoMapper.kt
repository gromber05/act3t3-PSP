package com.gromber05.act3t3.mappers

import com.gromber05.act3t3.domain.Categoria
import com.gromber05.act3t3.domainP.Producto
import com.gromber05.act3t3.dto.ProductoDto
import org.springframework.stereotype.Component

@Component
class ProductoMapper {

    fun toDto(entity: Producto): ProductoDto =
        ProductoDto(
            id = entity.id,
            nombre = entity.nombre,
            precio = entity.precio,
            stock = entity.stock,
            categoriaId = entity.categoria.id!!
        )

    fun toEntity(dto: ProductoDto, categoria: Categoria): Producto =
        Producto(
            id = dto.id,
            nombre = dto.nombre,
            precio = dto.precio,
            stock = dto.stock,
            categoria = categoria
        )
}
