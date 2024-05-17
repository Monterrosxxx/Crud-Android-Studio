package Modelo

import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {

    fun cadenaConexion(): Connection? {

        try {
            val url = "jdbc:oracle:thin:@10.10.3.42:1521:xe"
            val user = "system"
            val contrasena = "desarrollo"

            val connection = DriverManager.getConnection(url, user, contrasena)
            return connection
        }
        catch (e: Exception){
            println("Por chabacan aqui esta el error ve:$e")
            return null
        }

    }

}