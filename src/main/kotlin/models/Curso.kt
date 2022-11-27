package models

import java.util.UUID

class Curso() {
    lateinit var id: UUID
    lateinit var nombre: String
    lateinit var alumnos: List<Alumno>

    constructor(
        id: UUID?,
        nombre: String,
        alumnos: List<Alumno>?
    ) : this() {
        this.id = id ?: UUID.randomUUID()
        this.nombre = nombre
        this.alumnos = alumnos ?: listOf()
    }
}