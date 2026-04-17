package mx.edu.unpa.miandroid.service

import mx.edu.unpa.miandroid.model.Usuario
import retrofit2.Call
import retrofit2.http.*
interface Usuarioservice {
    @GET("usuario/app")
    fun getUsuario(): Call<List<Usuario>>

    @POST("usuario/app")
    fun crearUsuario(@Body usuario: Usuario):Call<Usuario>

}