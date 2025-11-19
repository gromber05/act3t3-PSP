package com.gromber05.act3t3.service

import com.gromber05.act3t3.domain.Categoria
import com.gromber05.act3t3.dto.CategoriaConProductosDto
import com.gromber05.act3t3.dto.CategoriaDto
import com.gromber05.act3t3.handler.NotFoundException
import com.gromber05.act3t3.mappers.CategoriaMapper
import com.gromber05.act3t3.repository.CategoriaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoriaService(
    private val categoriaRepository: CategoriaRepository,
    private val categoriaMapper: CategoriaMapper
) {

    @Transactional(readOnly = true)
    fun findAll(): List<CategoriaDto> =
        categoriaRepository.findAll().map { categoriaMapper.toDto(it) }

    @Transactional(readOnly = true)
    fun findById(id: Long): CategoriaDto =
        categoriaRepository.findById(id)
            .map { categoriaMapper.toDto(it) }
            .orElseThrow { NotFoundException("Categoría con id $id no encontrada") }

    @Transactional(readOnly = true)
    fun findCategoriaConProductos(id: Long): CategoriaConProductosDto {
        val categoria = categoriaRepository.findById(id)
            .orElseThrow { NotFoundException("Categoría con id $id no encontrada") }
        return categoriaMapper.toCategoriaConProductosDto(categoria)
    }

    @Transactional
    fun create(dto: CategoriaDto): CategoriaDto {
        val entity = categoriaMapper.toEntity(dto.copy(id = null))
        val saved = categoriaRepository.save(entity)
        return categoriaMapper.toDto(saved)
    }

    @Transactional
    fun update(id: Long, dto: CategoriaDto): CategoriaDto {
        val existing = categoriaRepository.findById(id)
            .orElseThrow { NotFoundException("Categoría con id $id no encontrada") }

        val updated = existing.copy(
            nombre = dto.nombre,
            descripcion = dto.descripcion
        )

        return categoriaMapper.toDto(categoriaRepository.save(updated))
    }

    @Transactional
    fun delete(id: Long) {
        if (!categoriaRepository.existsById(id)) {
            throw NotFoundException("Categoría con id $id no encontrada")
        }
        categoriaRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun getEntityById(id: Long): Categoria =
        categoriaRepository.findById(id)
            .orElseThrow { NotFoundException("Categoría con id $id no encontrada") }
}
