package com.angel.delta_v.ui.viewmodel

import com.angel.delta_v.data.Launch

/**
 * El 'sealed interface' solo deja tener 4 estados.
 *
 * Y el Loading y Empty son Object porque no necesitan pasar información
 * El Success y Error son data class porque pasan información
 *
 *
 * - El Loading es el estado inicial y el que sale cuando carga
 * - El Empty es para cuando funciona pero no hay información
 * - El Success es para cuendo va todo perfecto y funciona
 * - El de Error cuendo falla
 */
sealed interface UiState {
    object Loading : UiState
    object Empty : UiState
    data class Success(val launches: List<Launch>) : UiState
    data class Error(val message: String) : UiState
}