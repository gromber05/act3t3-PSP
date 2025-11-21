package com.gromber05.act3t3.repository

import com.gromber05.act3t3.domain.Productos
import org.springframework.data.jpa.repository.JpaRepository

interface ProductoRepository : JpaRepository<Productos, Long> {
    fun findByCategoriaId(categoriaId: Long): List<Productos>
}
