package repositories

import entities.CursoDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import mappers.fromCursoDao
import models.Curso
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import java.util.UUID

class CursoRepository(
    private val cursoDao: UUIDEntityClass<CursoDao>
): ICRUDRepository<Curso, UUID> {
    override suspend fun findAll(): Flow<Curso> = newSuspendedTransaction(Dispatchers.IO) {
        cursoDao.all().map { it.fromCursoDao() }.asFlow()
    }

    override suspend fun findById(id: UUID): Deferred<Curso?> = suspendedTransactionAsync(Dispatchers.IO) {
        cursoDao.findById(id)?.fromCursoDao()
    }

    override suspend fun create(entity: Curso): Deferred<Curso> = suspendedTransactionAsync(Dispatchers.IO) {
        val existe = cursoDao.findById(entity.id)
        existe?.let {
            update(entity, it)
        }.run { insert(entity) }
    }

    private fun update(entity: Curso, existe: CursoDao): Curso {
        return existe.apply {
            nombre = entity.nombre
        }.fromCursoDao()
    }

    private fun insert(entity: Curso): Curso {
        return cursoDao.new(entity.id) {
            nombre = entity.nombre
        }.fromCursoDao()
    }

    override suspend fun delete(entity: Curso): Deferred<Boolean> = suspendedTransactionAsync(Dispatchers.IO) {
        val existe = cursoDao.findById(entity.id) ?: return@suspendedTransactionAsync false
        existe.delete()
        true
    }
}