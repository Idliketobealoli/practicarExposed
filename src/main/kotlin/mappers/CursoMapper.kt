package mappers

import entities.AlumnoDao
import entities.CursoDao
import models.Curso

fun CursoDao.fromCursoDao(): Curso {
    val alumnos = AlumnoDao.all().filter { it.curso?.id == id }.toList()
    return Curso(
        id = id.value,
        nombre = nombre,
        alumnos = alumnos.map { it.fromAlumnoDao() }
    )
}