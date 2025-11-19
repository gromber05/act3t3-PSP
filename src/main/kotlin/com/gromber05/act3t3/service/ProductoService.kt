package com.gromber05.act3t3.service

import com.gromber05.act3t3.dto.ProductoDto
import com.gromber05.act3t3.handler.NotFoundException
import com.gromber05.act3t3.mappers.ProductoMapper
import com.gromber05.act3t3.repository.ProductoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductoService(
    private val productoRepository: ProductoRepository,
    private val productoMapper: ProductoMapper,
    private val categoriaService: CategoriaService
) {

    @Transactional(readOnly = true)
    fun findAll(): List<ProductoDto> =
        productoRepository.findAll().map { productoMapper.toDto(it) }

    @Transactional(readOnly = true)
    fun findById(id: Long): ProductoDto =
        productoRepository.findById(id)
            .map { productoMapper.toDto(it) }
            .orElseThrow { NotFoundException("Producto con id $id no encontrado") }

    @Transactional(readOnly = true)
    fun findByCategoriaId(categoriaId: Long): List<ProductoDto> =
        productoRepository.findByCategoriaId(categoriaId)
            .map { productoMapper.toDto(it) }

    @Transactional
    fun create(dto: ProductoDto): ProductoDto {
        val categoria = categoriaService.getEntityById(dto.categoriaId)
        val entity = productoMapper.toEntity(dto.copy(id = null), categoria)
        val saved = productoRepository.save(entity)
        return productoMapper.toDto(saved)
    }

    @Transactional
    fun update(id: Long, dto: ProductoDto): ProductoDto {
        val existing = productoRepository.findById(id)
            .orElseThrow { NotFoundException("Producto con id $id no encontrado") }

        val categoria = categoriaService.getEntityById(dto.categoriaId)

        val updated = existing.copy(
            nombre = dto.nombre,
            precio = dto.precio,
            stock = dto.stock,
            categoria = categoria
        )

        return productoMapper.toDto(productoRepository.save(updated))
    }

    @Transactional
    fun delete(id: Long) {
        if (!productoRepository.existsById(id)) {
            throw NotFoundException("Producto con id $id no encontrado")
        }
        productoRepository.deleteById(id)
    }
}
