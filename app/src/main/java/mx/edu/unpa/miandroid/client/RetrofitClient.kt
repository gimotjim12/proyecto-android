package mx.edu.unpa.miandroid.client

import mx.edu.unpa.miandroid.service.Usuarioservice
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://10.0.2.2.8181/"

    val instance: Usuarioservice by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Usuarioservice::class.java)
    }
}