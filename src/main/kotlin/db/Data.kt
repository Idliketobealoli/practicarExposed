package db

import models.Alumno
import models.Curso
import java.util.*

fun getAlumnos() = listOf(
    Alumno(
        null,
        "Loli R.",
        6.9,
        UUID.fromString("93a98d69-0000-1111-0000-05b596ea83aa")
    ),
    Alumno(
        null,
        "Adolfo H.",
        9.9,
        UUID.fromString("93a98d69-0000-1111-0000-05b596ea83aa")
    ),
    Alumno(
        null,
        "Paco F.",
        1.0,
        UUID.fromString("93a98d69-0000-1111-0000-05b596ea83aa")
    )
)

fun getCursos() = listOf(
    Curso(
        UUID.fromString("93a98d69-0000-1111-0000-05b596ea83aa"),
        "DAM",
        getAlumnos()
    )
)

