package mappers

import entities.AlumnoDao
import models.Alumno

fun AlumnoDao.fromAlumnoDao(): Alumno {
    return Alumno(
        id = id.value,
        nombre = nombre,
        nota = nota,
        curso = curso?.id?.value
    )
}