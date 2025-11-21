package com.gromber05.act3t3.domain

import jakarta.persistence.*

@Entity
@Table(name = "categoria")
class Categoria() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var nombre: String = ""

    var descripcion: String? = null

    @OneToMany(mappedBy = "categoria", cascade = [CascadeType.ALL], orphanRemoval = true)
    var productos: MutableList<Productos> = mutableListOf()

    constructor(nombre: String, descripcion: String? = null) : this() {
        this.nombre = nombre
        this.descripcion = descripcion
    }
}

