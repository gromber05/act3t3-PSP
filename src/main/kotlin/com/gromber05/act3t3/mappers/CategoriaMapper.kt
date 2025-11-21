package com.gromber05.act3t3.mappers


import com.gromber05.act3t3.domain.Categoria
import com.gromber05.act3t3.dto.CategoriaConProductosDto
import com.gromber05.act3t3.dto.CategoriaDto
import org.springframework.stereotype.Component

@Component
class CategoriaMapper(
    private val productoMapper: ProductoMapper
) {

    fun toDto(entity: Categoria): CategoriaDto {
        return CategoriaDto(
            id = entity.id,
            nombre = entity.nombre,
            descripcion = entity.descripcion
        )
    }

    fun toEntity(dto: CategoriaDto): Categoria {
        val categoria = Categoria()
        categoria.id = dto.id
        categoria.nombre = dto.nombre
        categoria.descripcion = dto.descripcion
        return categoria
    }


    fun toCategoriaConProductosDto(entity: Categoria): CategoriaConProductosDto =
        CategoriaConProductosDto(
            id = entity.id,
            nombre = entity.nombre,
            descripcion = entity.descripcion,
            productos = entity.productos.map { productoMapper.toDto(it) }
        )


}
