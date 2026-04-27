package com.angel.delta_v.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.delta_v.ui.viewmodel.LaunchViewModel
import com.angel.delta_v.ui.viewmodel.UiState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.res.painterResource
import com.angel.delta_v.R
import androidx.compose.material3.ExperimentalMaterial3Api

/**
 * La función LaunchListScreen() se encarga de mostrar la lista de lanzamientos con los parametos:
 *  - viewModel para obtener los datos y los estados de la aplicación
 *  - onNavigateToDetail para navegar a la pantalla de detalles
 *
 * Creamos una variable para guardar el estado y lo evaluamos con el when
 *  - Loading: Muestra un CircularProgressIndicator
 *  - Empty: Muestra un texto indicando que no hay lanzamientos
 *  - Error: Muestra un icono de advertencia y un texto con el mensaje de error
 *  - Success: Muestra la lista de lanzamientos
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchListScreen(
    viewModel: LaunchViewModel,
    onNavigateToDetail: (String) -> Unit
) {


    // Creamos el Scaffold con el top bar que contiene el logo y el nombre de la app
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_logo),
                            contentDescription = "Logo de la app",
                            modifier = Modifier
                                .size(36.dp)
                                .padding(end = 8.dp)
                        )
                        Text("Delta V")
                    }
                }
            )
        }
    ) { paddingValues ->


        val estado by viewModel.uiState.collectAsState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        )
        {

            when (estado) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Empty -> {
                    Text("No hay lanzamientos programados actualmente.")
                }

                is UiState.Error -> {
                    val errorMessage = (estado as UiState.Error).message
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Advertencia",
                            tint = Color.Yellow
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Vaya, algo falló: $errorMessage")
                    }
                }

                is UiState.Success -> {
                    val lanzamientos = (estado as UiState.Success).launches

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(lanzamientos) { currentLaunch ->
                            LaunchItem(
                                launch = currentLaunch,
                                onClick = { onNavigateToDetail(currentLaunch.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}