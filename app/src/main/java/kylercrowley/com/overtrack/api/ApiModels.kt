package kylercrowley.com.overtrack.api

data class LootboxPatchNotesResponse(val patchNotes: List<LootboxPatchNote>,
                                val pagination: LootboxPatchNotesPagination)

data class LootboxPatchNote(
        val program: String,
        val locale: String,
        val type: String,
        val patchVersion: String,
        val status: String,
        val detail: String, // HTML
        val buildNumber: Int,
        val publish: Long, // Publish date (milliseconds)
        val created: Long, // Created date (milliseconds)
        val updated: Long, // Updated date (milliseconds)
        val develop: Boolean,
        val slug: String,
        val version: String
)

data class LootboxPatchNotesPagination(
        val totalEntries: Int,
        val totalPages: Int,
        val pageSize: Int,
        val page: Int
)

data class LootboxPlayerAchievementsResponse(
        val totalNumberOfAchievements: Int,
        val numberOfAchievementsCompleted: Int,
        val finishedAchievements: String,
        val achievements: List<LootboxAchievement>
)

data class LootboxAchievement(
        val name: String,
        val finished: Boolean,
        val image: String,
        val description: String,
        val category: String
)

data class LootboxPlayerProfileResponse(
        val data: LootboxPlayerProfile
)

data class LootboxPlayerProfile(
        val username: String,
        val level: Int,
        val games: LootboxGames,
        val playtime: LootboxPlaytime,
        val avatar: String,
        val competitive: LootboxCompetitive,
        val levelFrame: String,
        val star: String
)

data class LootboxGames(val quick: LootboxGameQuick, val competitive: LootboxGameCompetitive)
data class LootboxGameQuick(val wins: String)
class LootboxGameCompetitive()
data class LootboxPlaytime(val quick: String)
data class LootboxCompetitive(val rank: String, val rank_img: String)