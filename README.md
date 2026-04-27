# Delta-V 🚀



**Delta-V** es una aplicación de Android construida con Kotlin y Jetpack Compose que permite a los entusiastas del espacio seguir de cerca los próximos lanzamientos de cohetes en todo el mundo.



La aplicación consume datos en tiempo real de la API de [The Space Devs](https://thespacedevs.com/llapi) para ofrecer información detallada sobre misiones, cohetes y centros de lanzamiento.


## ✨ Características



- **Lista de Lanzamientos:** Visualiza los próximos lanzamientos espaciales con fechas y estados actualizados.

- **Detalles de Misión:** Información profunda sobre cada lanzamiento, incluyendo el cohete utilizado y la descripción de la misión.

- **Interfaz:** Construida totalmente con **Jetpack Compose**.

- **Gestión de Estado:** Implementación de `ViewModel` y `UiState` para una experiencia de usuario fluida y sin errores de carga.



## 🛠️ Stack Tecnológico



- **Lenguaje:** Kotlin

- **UI:** Jetpack Compose

- **Arquitectura:** MVVM (Model-View-ViewModel)



## 📦 Estructura del Proyecto



El proyecto sigue una estructura limpia organizada por capas:



- `data/`: Contiene los modelos de datos (`LaunchResponse`), la interfaz de la API (`SpaceDevsApi`) y el cliente de Retrofit.

- `ui/`:

- `screens/`: Pantallas de la interfaz (Lista, Detalle, Navegación).

- `viewmodel/`: Lógica y gestión del estado de la pantalla.

- `theme/`: Configuración de colores, tipografía .