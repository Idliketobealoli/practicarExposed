import config.AppConfig
import controllers.AlumnoController
import controllers.CursoController
import db.DataBaseManager
import db.getAlumnos
import db.getCursos
import kotlinx.coroutines.*
import java.io.File

fun main(args: Array<String>) = runBlocking {
    initDB()

    val loadAlumnos = launch(Dispatchers.IO) { getAlumnos().forEach { AlumnoController.create(it) } }

    println("loading data")
    while (!loadAlumnos.isCompleted) {
        print(".")
        delay(500)
    }
    loadAlumnos.join()
    val loadCursos = launch(Dispatchers.IO) { getCursos().forEach { CursoController.create(it) } }
    loadCursos.join()
}

fun initDB() {
    val appConfig = AppConfig.fromPropertiesFile("${System.getProperty("user.dir")}${File.separator}" +
            "src${File.separator}main${File.separator}resources${File.separator}config.properties")
    println("Configuration: $appConfig")
    DataBaseManager.init(appConfig)
}