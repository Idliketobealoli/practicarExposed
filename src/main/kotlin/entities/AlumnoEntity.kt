package entities

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object AlumnoTable: UUIDTable("Alumnos") {
    val nombre = varchar("nombre", 255)
    val nota = double("nota")
    val curso = reference("curso", CursoTable).nullable()
}

class AlumnoDao(id: EntityID<UUID>): UUIDEntity(id) {
    companion object : UUIDEntityClass<AlumnoDao>(AlumnoTable)
    var nombre by AlumnoTable.nombre
    var nota by AlumnoTable.nota
    var curso by CursoDao optionalReferencedOn AlumnoTable.curso
}