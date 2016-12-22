package kylercrowley.com.overtrack

val CACHE_FILE_NAME = "okhttp_cache"
val CACHE_FILE_SIZE = 10 * 1000 * 1000.toLong() // 10 MB

val PATCH_NOTE_HTML_KEY = "patch_note_html"

val PLATFORMS = arrayOf(
        Platform("pc", "PC"),
        Platform("xbl", "Xbox Live (XBL)"),
        Platform("psn", "Playstation Network (PSN)")
)

val REGIONS = arrayOf(
        Region("eu", "EU"),
        Region("us", "US"),
        Region("kr", "KR"),
        Region("cn", "CN"),
        Region("global", "Global")
)

val PROFILE_ARRAY_KEY = "profile"

