package entities

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object CursoTable: UUIDTable("Cursos") {
    val nombre = varchar("nombre", 255)
}

class CursoDao(id: EntityID<UUID>): UUIDEntity(id) {
    companion object : UUIDEntityClass<CursoDao>(CursoTable)
    var nombre by CursoTable.nombre
}