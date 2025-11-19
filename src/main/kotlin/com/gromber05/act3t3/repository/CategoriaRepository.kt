package com.gromber05.act3t3.repository

import com.gromber05.act3t3.domain.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoriaRepository : JpaRepository<Categoria, Long> {
    fun findByNombreIgnoreCase(nombre: String): Optional<Categoria>
}
