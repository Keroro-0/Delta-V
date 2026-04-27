package com.angel.delta_v.data

data class LaunchResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Launch>
)

data class Launch(
    val id: String,
    val name: String,
    val status: LaunchStatus,
    val net: String,
    val launch_service_provider: ServiceProvider,
    val image: String?,
    val mission: Mission?,
    val rocket: Rocket?,
    val pad: Pad?,
    val webcast_live: Boolean,
    val vid_urls: List<VidUrl>?
)

data class LaunchStatus(
    val id: Int,
    val name: String,
    val abbrev: String,
    val description: String
)

data class ServiceProvider(
    val id: Int,
    val name: String,
    val type: String?
)

data class Mission(
    val id: Int,
    val name: String,
    val description: String?,
    val type: String?,
    val orbit: Orbit?
)

data class Rocket(
    val configuration: Configuration?
)

data class Configuration(
    val id: Int,
    val name: String,
    val family: String?
)

data class Pad(
    val id: Int,
    val name: String,
    val location: Location?
)

data class Location(
    val id: Int,
    val name: String
)

data class Orbit(
    val id: Int,
    val name: String,
    val abbrev: String
)

data class VidUrl(
    val url: String,
    val title: String?,
    val description: String?
)