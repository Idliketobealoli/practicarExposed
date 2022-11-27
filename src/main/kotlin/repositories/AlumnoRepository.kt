package repositories

import entities.AlumnoDao
import entities.CursoDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import mappers.fromAlumnoDao
import models.Alumno
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import java.util.UUID

class AlumnoRepository(
    private val alumnoDao: UUIDEntityClass<AlumnoDao>,
    private val cursoDao: UUIDEntityClass<CursoDao>
): ICRUDRepository<Alumno, UUID> {
    override suspend fun findAll(): Flow<Alumno> = newSuspendedTransaction(Dispatchers.IO) {
        alumnoDao.all().map { it.fromAlumnoDao() }.asFlow()
    }

    override suspend fun findById(id: UUID): Deferred<Alumno?> = suspendedTransactionAsync(Dispatchers.IO) {
        alumnoDao.findById(id)?.fromAlumnoDao()
    }

    override suspend fun create(entity: Alumno): Deferred<Alumno> = suspendedTransactionAsync(Dispatchers.IO) {
        val existe = alumnoDao.findById(entity.id)
        existe?.let { update(entity,it) }.run { insert(entity) }
    }

    private fun insert(entity: Alumno): Alumno {
        return alumnoDao.new(entity.id) {
            nombre = entity.nombre
            nota = entity.nota
            curso = entity.curso?.let { cursoDao.findById(it) }
        }.fromAlumnoDao()
    }

    private fun update(entity: Alumno, existe: AlumnoDao): Alumno {
        return existe.apply {
            nombre = entity.nombre
            nota = entity.nota
            curso = entity.curso?.let { cursoDao.findById(it) }
        }.fromAlumnoDao()
    }

    override suspend fun delete(entity: Alumno): Deferred<Boolean> {
        TODO("Not yet implemented")
    }
}