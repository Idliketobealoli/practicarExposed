package controllers

import entities.AlumnoDao
import entities.CursoDao
import kotlinx.coroutines.flow.toList
import models.Alumno
import repositories.AlumnoRepository
import java.util.UUID

object AlumnoController {
    val repo = AlumnoRepository(AlumnoDao, CursoDao)

    suspend fun findAll(): String {
        return repo.findAll().toList().toString()
    }

    suspend fun findById(id: UUID): String {
        return repo.findById(id).await()?.toString() ?: "User with id $id not found"
    }

    suspend fun create(item: Alumno): String {
        return repo.create(item).await().toString()
    }

    suspend fun delete(item: Alumno): String {
        val result = repo.delete(item).await()
        return if (result) item.toString()
        else "unable to delete ${item.nombre}"
    }
}