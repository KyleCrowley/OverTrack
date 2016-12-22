package kylercrowley.com.overtrack.utils

class ProfileUtils {

    companion object {

        fun getStars(level: Int): String {
            val count = level / 100

            return "★".repeat(count)
        }
    }
}