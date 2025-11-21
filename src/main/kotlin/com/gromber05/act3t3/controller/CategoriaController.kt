package com.gromber05.act3t3.controller

import com.gromber05.act3t3.dto.CategoriaConProductosDto
import com.gromber05.act3t3.dto.CategoriaDto
import com.gromber05.act3t3.service.CategoriaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categoria")
class CategoriaController(
    private val categoriaService: CategoriaService
) {

    @GetMapping
    fun getAll(): List<CategoriaDto> =
        categoriaService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): CategoriaDto =
        categoriaService.findById(id)

    @GetMapping("/{id}/productos")
    fun getCategoriaConProductos(@PathVariable id: Long): CategoriaConProductosDto =
        categoriaService.findCategoriaConProductos(id)

    @PostMapping
    fun create(@RequestBody dto: CategoriaDto): ResponseEntity<CategoriaDto> {
        val created = categoriaService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody dto: CategoriaDto
    ): CategoriaDto =
        categoriaService.update(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        categoriaService.delete(id)
    }
}
