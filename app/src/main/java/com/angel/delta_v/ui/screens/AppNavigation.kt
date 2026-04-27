package com.angel.delta_v.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angel.delta_v.ui.viewmodel.LaunchViewModel

/**
 *
 * La función AppNavigation() se encarga de gestionar la navegación entre pantallas
 *
 *  - El navController es el que se encarga de cambiar de pantalla y guarda el estado de cada pantalla
 *  - El launchViewModel se encarga de guardar los datos
 *
 * Creamos el NavHost que que tiene las pantallas de mi aplicación, le ponemeos como predeterminada la listaLanzamientos
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val launchViewModel: LaunchViewModel = viewModel()


    NavHost(navController = navController, startDestination = "listaLanzamientos") {

        composable("listaLanzamientos") {
            LaunchListScreen(
                viewModel = launchViewModel,
                onNavigateToDetail = {
                    idSeleccionado -> navController.navigate("detalles/$idSeleccionado")
                }
            )
        }

        composable("detalles/{launchId}") {
            backStackEntry -> val launchId = backStackEntry.arguments?.getString("launchId")

            if (launchId != null) {
                LaunchDetailScreen(
                    launchId = launchId,
                    viewModel = launchViewModel,
                    onBack = { navController.popBackStack()}
                )
            }
        }
    }
}