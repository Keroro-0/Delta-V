package com.angel.delta_v.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angel.delta_v.data.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/**
 *  LaunchViewModel es el que se encarga de gestionar los estados
 *
 *  - _uiState es una variable privada y mutable que guarda el estado de la aplicación y solo puede ser modificada por el ViewModel
 *  y tiene como predefinido el valor Loading
 *  - uiState es una variable pública y solo de lectura que expone el estado de la aplicación
 *
 *  El bloque init se ejecuta al crear el ViewModel y llama a fetchLaunches() para descargar los datos
 *
 */

class LaunchViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState


    init {
        fetchLaunches()
    }



    /**
     * La función fetchLaunches se encarga de trar los datos y actualizar el estado
     *
     * viewModelScope lanza un hilo secundario unido al ViewModel para cargar la infromación
     *
     * Nada mas entrar pone el estado en Loading y dentro del try hacemos una llamada a la api para que nos de los lanzamientos
     * y los guardamo en la variable response
     *
     * Se comprueba si la  lista está vacía :
     *  - Si está vacía, se pone el estado en Empty
     *  - Si no está vacía, se pone el estado en Success y se le pasa la lista de lanzamientos
     *
     * En caso de error entra al catch y pone el estado en Error
     */
    fun fetchLaunches() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            try {
                val response = RetrofitClient.api.getUpcomingLaunches()

                if (response.results.isEmpty()) {
                    _uiState.value = UiState.Empty
                } else {
                    _uiState.value = UiState.Success(response.results)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error al cargar datos: ${e.message}")
            }
        }
    }
}