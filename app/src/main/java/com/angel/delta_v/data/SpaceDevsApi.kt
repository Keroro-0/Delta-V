package com.angel.delta_v.data

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interfaz que usa Retrofit para saber a qué  URL tiene que llamar.
 */
interface SpaceDevsApi {

    // Uso la anotación @GET para indicar que voy a pedir datos a la ruta de próximos lanzamientos
    @GET("launch/upcoming/")
    suspend fun getUpcomingLaunches(
        @Query("limit") limit: Int = 50
    ): LaunchResponse


}