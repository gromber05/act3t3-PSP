package com.gromber05.act3t3.repository

import com.gromber05.act3t3.domainP.Producto
import org.springframework.data.jpa.repository.JpaRepository

interface ProductoRepository : JpaRepository<Producto, Long> {
    fun findByCategoriaId(categoriaId: Long): List<Producto>
}
