package com.angel.delta_v.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * El objeto RetrofitClient se utiliza para crear una instancia de Retrofit que se utilizará para hacer las llamadas a la API.
 */
object RetrofitClient {
    // URL base de la API
    private const val BASE_URL = "https://ll.thespacedevs.com/2.2.0/"

    // Instancia de Retrofit
    val api: SpaceDevsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Añado Gson para que convierta automáticamente el texto JSON de internet en mis Data Classes de Kotlin
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpaceDevsApi::class.java)
    }
}