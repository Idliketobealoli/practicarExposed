package models

import java.util.UUID

class Alumno() {
    lateinit var id: UUID
    lateinit var nombre: String
    var nota: Double = 0.0
    var curso: UUID? = null

    constructor(
        id: UUID?,
        nombre: String,
        nota: Double,
        curso: UUID?
    ) : this () {
        this.id = id ?: UUID.randomUUID()
        this.nombre = nombre
        this.nota = nota
        this.curso = curso
    }

    override fun toString(): String {
        return "Alumno(" +
                "id=$id," +
                "nombre=$nombre," +
                "nota=$nota," +
                "curso=$curso)"
    }
}