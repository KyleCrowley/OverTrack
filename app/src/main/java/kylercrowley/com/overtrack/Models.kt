package kylercrowley.com.overtrack

data class Platform(
        val value: String,
        val label: String
)

data class Region(
        val value: String,
        val label: String
)

data class StatHeader(
        //val imageUrl: String?,
        val name: String
)

data class Stat(
        val name: String,
        val value: String
)