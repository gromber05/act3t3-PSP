package com.gromber05.act3t3.controller

import com.gromber05.act3t3.dto.ProductoDto
import com.gromber05.act3t3.service.ProductoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/productos")
class ProductoController(
    private val productoService: ProductoService
) {

    @GetMapping
    fun getAll(): List<ProductoDto> =
        productoService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ProductoDto =
        productoService.findById(id)

    @GetMapping("/categoria/{categoriaId}")
    fun getByCategoria(@PathVariable categoriaId: Long): List<ProductoDto> =
        productoService.findByCategoriaId(categoriaId)

    @PostMapping
    fun create(@RequestBody dto: ProductoDto): ResponseEntity<ProductoDto> {
        val created = productoService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody dto: ProductoDto
    ): ProductoDto =
        productoService.update(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        productoService.delete(id)
    }
}
