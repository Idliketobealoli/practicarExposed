package controllers

import entities.CursoDao
import kotlinx.coroutines.flow.toList
import models.Curso
import repositories.CursoRepository
import java.util.*

object CursoController {
    val repo = CursoRepository(CursoDao)

    suspend fun findAll(): String {
        return repo.findAll().toList().toString()
    }

    suspend fun getById(id: UUID): String {
        return repo.findById(id).await()?.toString() ?: "Curso with id $id not found"
    }

    suspend fun create(curso: Curso): String {
        return repo.create(curso).await().toString()
    }

    suspend fun delete(curso: Curso): String {
        val existe = repo.delete(curso).await()
        return if (existe) { curso.toString() }
        else "could not delete ${curso.nombre}"
    }
}