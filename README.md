\# Delta-V 🚀



\*\*Delta-V\*\* es una aplicación de Android moderna construida con Kotlin y Jetpack Compose que permite a los entusiastas del espacio seguir de cerca los próximos lanzamientos de cohetes en todo el mundo.



La aplicación consume datos en tiempo real de la API de \[The Space Devs](https://thespacedevs.com/llapi) para ofrecer información detallada sobre misiones, cohetes y centros de lanzamiento.



\## ✨ Características



\- \*\*Lista de Lanzamientos:\*\* Visualiza los próximos lanzamientos espaciales con fechas y estados actualizados.

\- \*\*Detalles de Misión:\*\* Información profunda sobre cada lanzamiento, incluyendo el cohete utilizado y la descripción de la misión.

\- \*\*Interfaz Moderna:\*\* Construida totalmente con \*\*Jetpack Compose\*\*, siguiendo las guías de Material Design 3.

\- \*\*Gestión de Estado:\*\* Implementación de `ViewModel` y `UiState` para una experiencia de usuario fluida y sin errores de carga.



\## 🛠️ Stack Tecnológico



\- \*\*Lenguaje:\*\* \[Kotlin](https://kotlinlang.org/)

\- \*\*UI:\*\* \[Jetpack Compose](https://developer.android.com/jetpack/compose)

\- \*\*Networking:\*\* \[Retrofit](https://square.github.io/retrofit/) \& OkHttp

\- \*\*Serialización:\*\* Gson (para el parseo de JSON)

\- \*\*Asincronía:\*\* Coroutines \& Flow

\- \*\*Arquitectura:\*\* MVVM (Model-View-ViewModel)

\- \*\*Imagen:\*\* (Sugerencia: Coil para cargar las imágenes de los cohetes)



\## 📦 Estructura del Proyecto



El proyecto sigue una estructura limpia organizada por capas:



\- `data/`: Contiene los modelos de datos (`LaunchResponse`), la interfaz de la API (`SpaceDevsApi`) y el cliente de Retrofit.

\- `ui/`:

&#x20;   - `screens/`: Pantallas de la interfaz (Lista, Detalle, Navegación).

&#x20;   - `viewmodel/`: Lógica de negocio y gestión del estado de la pantalla.

&#x20;   - `theme/`: Configuración de colores, tipografía y temas (Material3).



\## 🚀 Instalación y Uso



1\. \*\*Clonar el repositorio:\*\*

&#x20;  ```bash

&#x20;  git clone \[https://github.com/tu-usuario/delta-v.git](https://github.com/tu-usuario/delta-v.git)

