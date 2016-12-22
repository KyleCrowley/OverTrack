package kylercrowley.com.overtrack.api

import com.squareup.moshi.Json

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

data class LootboxPlayerAllHeroStats (
    // COMBAT //
    var MeleeFinalBlow: String?,
    var MeleeFinalBlows: String?,

    var SoloKill: String?,
    var SoloKills: String?,

    var ObjectiveKill: String?,
    var ObjectiveKills: String?,

    var FinalBlow: String?,
    var FinalBlows: String?,

    var DamageDone: String?,

    var Elimination: String?,
    var Eliminations: String?,

    var EnvironmentalKill: String?,
    var EnvironmentalKills: String?,

    var Multikill: String?,
    var Multikills: String?,

    // ASSISTS //

    var ReconAssist: String?,
    var ReconAssists: String?,

    var HealingDone: String?,

    var TeleporterPadDestroyed: String?,
    var TeleporterPadsDestroyed: String?,

    // BEST //

    @Json(name = "Eliminations-MostinGame")
    var EliminationsMostinGame: String?,

    @Json(name = "FinalBlows-MostinGame")
    var FinalBlowsMostinGame: String?,

    @Json(name = "DamageDone-MostinGame")
    var DamageDoneMostinGame: String?,

    @Json(name = "HealingDone-MostinGame")
    var HealingDoneMostinGame: String?,

    @Json(name = "DefensiveAssists-MostinGame")
    var DefensiveAssistsMostinGame: String?,

    @Json(name = "OffensiveAssists-MostinGame")
    var OffensiveAssistsMostinGame: String?,

    @Json(name = "ObjectiveKills-MostinGame")
    var ObjectiveKillsMostinGame: String?,

    @Json(name = "ObjectiveTime-MostinGame")
    var ObjectiveTimeMostinGame: String?,

    @Json(name = "Multikill-Best")
    var MultikillBest: String?,

    @Json(name = "SoloKills-MostinGame")
    var SoloKillsMostinGame: String?,

    @Json(name = "TimeSpentonFire-MostinGame")
    var TimeSpentonFireMostinGame: String?,

    // AVERAGE //

    @Json(name = "MeleeFinalBlows-Average")
    var MeleeFinalBlowsAverage: String?,

    @Json(name = "TimeSpentonFire-Average")
    var TimeSpentonFireAverage: String?,

    @Json(name = "SoloKills-Average")
    var SoloKillsAverage: String?,

    @Json(name = "ObjectiveTime-Average")
    var ObjectiveTimeAverage: String?,

    @Json(name = "ObjectiveKills-Average")
    var ObjectiveKillsAverage: String?,

    @Json(name = "HealingDone-Average")
    var HealingDoneAverage: String?,

    @Json(name = "FinalBlows-Average")
    var FinalBlowsAverage: String?,

    @Json(name = "Deaths-Average")
    var DeathsAverage: String?,

    @Json(name = "DamageDone-Average")
    var DamageDoneAverage: String?,

    @Json(name = "Eliminations-Average")
    var EliminationsAverage: String?,

    // DEATHS //

    var Death: String?,
    var Deaths: String?,

    var EnvironmentalDeath: String?,
    var EnvironmentalDeaths: String?,

    // MATCH AWARDS //

    var Card: String?,
    var Cards: String?,

    var Medal: String?,
    var Medals: String?,

    @Json(name = "Medal-Gold")
    var MedalGold: String?,
    @Json(name = "Medals-Gold")
    var MedalsGold: String?,

    @Json(name = "Medal-Silver")
    var MedalSilver: String?,
    @Json(name = "Medals-Silver")
    var MedalsSilver: String?,

    @Json(name = "Medal-Bronze")
    var MedalBronze: String?,
    @Json(name = "Medals-Bronze")
    var MedalsBronze: String?,

    // GAME //

    var GamePlayed: String?,
    var GamesPlayed: String?,

    var GameWon: String?,
    var GamesWon: String?,

    var TimeSpentonFire: String?,

    var ObjectiveTime: String?,

    var TimePlayed: String?,

    // MISCELLANEOUS //

    var MeleeFinalBlowMostinGame: String?,
    var GamesTied: String?,
    var GamesLost: String?,
    var DefensiveAssists: String?,
    var DefensiveAssistsAverage: String?,
    var OffensiveAssists: String?,
    var OffensiveAssistsAverage: String?
)