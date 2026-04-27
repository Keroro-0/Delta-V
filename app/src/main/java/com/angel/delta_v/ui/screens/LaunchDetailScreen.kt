package com.angel.delta_v.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.angel.delta_v.ui.viewmodel.LaunchViewModel
import com.angel.delta_v.ui.viewmodel.UiState


/**
 *
 * La función LaunchDetailScreen() se encarga de mostrar los detalles del lanzamiento
 *  - launchId para saber que lanzamiento mostrar
 *  - viewModel para obtener los datos y los estados de la aplicación
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchDetailScreen(launchId: String, viewModel: LaunchViewModel , onBack: () -> Unit) {
    val state by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    // Si el estado es Success, buscamos el lanzamiento por su ID
    val launch = if (state is UiState.Success) {
        (state as UiState.Success).launches.find { it.id == launchId }
    } else null

    // Comprobamos si el lanzamiento tiene un video
    val videoUrl = launch?.vid_urls?.firstOrNull()?.url
    val isAvailable = !videoUrl.isNullOrEmpty()

    // Con el Scaffold creamos la barra superior topBar
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Lanzamiento") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, // Flecha estándar de Android
                            contentDescription = "Volver atrás"
                        )
                    }
                },
                // Hacemos que la barra sea algo transparente para que no desentone
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
                )
            )
        }
    ) { paddingValues ->

    // Si el lanzamiento no es nulo, mostramos los detalles, si no, mostramos un mensaje de error
    if (launch == null) {
        ErrorMessage()
    } else {

        // Usamos LazyColumn para que toda la pantalla tenga scroll
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Cabecera con Imagen y Graduado
            item {
                Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                    AsyncImage(
                        model = launch.image,
                        contentDescription = launch.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    // Degradado para que el nombre se lea bien sobre la imagen
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.7f)
                                    ),
                                    startY = 400f
                                )
                            )
                    )
                    // Nombre y Agencia encima de la imagen
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp)
                    ) {
                        Text(
                            text = launch.name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = launch.launch_service_provider.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                }
            }

            // Información
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // Estado del lanzamiento
                    StatusBadge(launch.status.name)

                    Spacer(modifier = Modifier.height(24.dp))

                    // Infromación del vuelo
                    Text(
                        "Información del Vuelo",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        InfoCard(
                            Modifier.weight(1f),
                            Icons.Default.DateRange,
                            "Fecha",
                            launch.net.take(10)
                        )
                        InfoCard(
                            Modifier.weight(1f),
                            Icons.Default.Schedule,
                            "Hora (UTC)",
                            launch.net.substringAfter("T").take(5)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        InfoCard(
                            Modifier.weight(1f),
                            Icons.Default.RocketLaunch,
                            "Vehículo",
                            launch.rocket?.configuration?.name ?: "N/A"
                        )
                        InfoCard(
                            Modifier.weight(1f),
                            Icons.Default.Public,
                            "Órbita",
                            launch.mission?.orbit?.abbrev ?: "N/A"
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Lugar de lanzamiento
                    SectionTitle(Icons.Default.LocationOn, "Lugar de Lanzamiento")
                    Text(
                        text = "${launch.pad?.name}\n${launch.pad?.location?.name}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 32.dp, top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Detalles de la misión
                    SectionTitle(Icons.Default.Assignment, "Detalles de la Misión")
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(
                                alpha = 0.4f
                            )
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Tipo: ${launch.mission?.type ?: "Desconocido"}",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = launch.mission?.description
                                    ?: "No hay descripción disponible para esta misión.",
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 20.sp
                            )
                        }
                    }

                    // Boton para ver la retrasmisión

                    Button(
                        onClick = { videoUrl?.let { uriHandler.openUri(it) } },
                        enabled = isAvailable,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            // Colores cuando está ACTIVO
                            containerColor = Color(0xFFCD201F),
                            contentColor = Color.White,
                            // Colores cuando está DESACTIVADO
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(
                                alpha = 0.5f
                            ),
                            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                alpha = 0.3f
                            )
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 6.dp,
                            pressedElevation = 2.dp,
                            disabledElevation = 0.dp
                        )
                    ) {
                        // El icono cambia si está disponible o no
                        Icon(
                            imageVector = if (isAvailable) Icons.Default.PlayArrow else Icons.Default.VideocamOff,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        // Texto dinámico
                        Text(
                            text = when {
                                isAvailable && launch.webcast_live -> "VER DIRECTO AHORA"
                                isAvailable -> "VER RETRANSMISIÓN"
                                else -> "TRANSMISIÓN NO DISPONIBLE"
                            },
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 1.1.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(40.dp))

                }
            }
        }
    }
    }
}

// Mensaje de error
@Composable
fun ErrorMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Error",
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No se ha encontrado el lanzamiento",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

// Estado del lanzamiento que cambia de color según su estado
@Composable
fun StatusBadge(status: String) {
    val backgroundColor = when {
        status.contains("Success") -> Color(0xFFE8F5E9)
        status.contains("Go") -> Color(0xFFE3F2FD)
        else -> Color(0xFFFFF3E0)
    }
    val contentColor = when {
        status.contains("Success") -> Color(0xFF2E7D32)
        status.contains("Go") -> Color(0xFF1565C0)
        else -> Color(0xFFEF6C00)
    }

    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = status.uppercase(),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

// Tarjetas de infromación del lanzamiento
@Composable
fun InfoCard(modifier: Modifier, icon: ImageVector, label: String, value: String) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = CardDefaults.outlinedCardBorder(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, maxLines = 1)
        }
    }
}

// Título para las secciones
@Composable
fun SectionTitle(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    }
}