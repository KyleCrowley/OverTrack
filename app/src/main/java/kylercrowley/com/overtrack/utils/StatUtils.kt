package kylercrowley.com.overtrack.utils

import kylercrowley.com.overtrack.Stat
import kylercrowley.com.overtrack.StatHeader
import kylercrowley.com.overtrack.api.LootboxPlayerAllHeroStats

class StatUtils {

    companion object {

        fun handlePlurals(string1: String?, string2: String?): String {
            if (string1 != null) return string1
            else if (string2 != null) return string2
            else return "0"
        }

        /**
         * This function exists solely to organize the otherwise unordered list of stats from Lootbox API.
         */
        fun getAllHeroStatsList(categoryNames: Array<String>, stats: LootboxPlayerAllHeroStats): List<Any> {
            return mutableListOf(
                    // Combat Category
                    StatHeader(categoryNames[0]),
                    Stat("Melee Final Blow(s)", handlePlurals(stats.MeleeFinalBlow, stats.MeleeFinalBlows)),
                    Stat("Solo Kill(s)", handlePlurals(stats.SoloKill, stats.SoloKills)),
                    Stat("Objective Kill(s)", handlePlurals(stats.ObjectiveKill, stats.ObjectiveKills)),
                    Stat("Final Blow(s)", handlePlurals(stats.FinalBlow, stats.FinalBlows)),
                    Stat("Damage Done", handlePlurals(stats.DamageDone, null)),
                    Stat("Elimination(s)", handlePlurals(stats.Elimination, stats.Eliminations)),
                    Stat("Environmental Kill(s)", handlePlurals(stats.EnvironmentalKill, stats.EnvironmentalKills)),
                    Stat("Multi-kill(s)", handlePlurals(stats.Multikill, stats.Multikills)),

                    // Assists Category
                    StatHeader(categoryNames[1]),
                    Stat("Recon Assist(s)", handlePlurals(stats.ReconAssist, stats.ReconAssists)),
                    Stat("Healing Done", handlePlurals(stats.HealingDone, null)),
                    Stat("Teleporter Pad(s) Destroyed", handlePlurals(stats.TeleporterPadDestroyed, stats.TeleporterPadsDestroyed)),

                    // Best Category
                    StatHeader(categoryNames[2]),
                    Stat("Eliminations - Most in Game", handlePlurals(stats.EliminationsMostinGame, null)),
                    Stat("Final Blows - Most in Game", handlePlurals(stats.FinalBlowsMostinGame, null)),
                    Stat("Damage Done - Most in Game", handlePlurals(stats.DamageDoneMostinGame, null)),
                    Stat("Healing Done - Most in Game", handlePlurals(stats.HealingDoneMostinGame, null)),
                    Stat("Defensive Assists - Most in Game", handlePlurals(stats.DefensiveAssistsMostinGame, null)),
                    Stat("Offensive Assists - Most in Game", handlePlurals(stats.OffensiveAssistsMostinGame, null)),
                    Stat("Objective Kills - Most in Game", handlePlurals(stats.ObjectiveKillsMostinGame, null)),
                    Stat("Objective Time - Most in Game", handlePlurals(stats.ObjectiveTimeMostinGame, null)),
                    Stat("Multikill - Most in Game", handlePlurals(stats.MultikillBest, null)),
                    Stat("Solo Kills - Most in Game", handlePlurals(stats.SoloKillsMostinGame, null)),
                    Stat("Time Spent on Fire - Most in Game", handlePlurals(stats.TimeSpentonFireMostinGame, null)),

                    // Average Category
                    StatHeader(categoryNames[3]),
                    Stat("Melee Final Blows - Average", handlePlurals(stats.MeleeFinalBlowsAverage, null)),
                    Stat("Time Spent on Fire - Average", handlePlurals(stats.TimeSpentonFireAverage, null)),
                    Stat("Solo Kills - Average", handlePlurals(stats.SoloKillsAverage, null)),
                    Stat("Objective Time - Average", handlePlurals(stats.ObjectiveTimeAverage, null)),
                    Stat("Objective Kills - Average", handlePlurals(stats.ObjectiveKillsAverage, null)),
                    Stat("Healing Done - Average", handlePlurals(stats.HealingDoneAverage, null)),
                    Stat("Final Blows - Average", handlePlurals(stats.FinalBlowsAverage, null)),
                    Stat("Deaths - Average", handlePlurals(stats.DeathsAverage, null)),
                    Stat("Damage Done - Average", handlePlurals(stats.DamageDoneAverage, null)),
                    Stat("Eliminations - Average", handlePlurals(stats.EliminationsAverage, null)),

                    // Deaths Category
                    StatHeader(categoryNames[4]),
                    Stat("Death(s)", handlePlurals(stats.Death, stats.Deaths)),
                    Stat("Environmental Death(s)", handlePlurals(stats.EnvironmentalDeath, stats.EnvironmentalDeaths)),

                    // Match Awards
                    StatHeader(categoryNames[5]),
                    Stat("Card(s)", handlePlurals(stats.Card, stats.Cards)),
                    Stat("Medals(s)", handlePlurals(stats.Medal, stats.Medals)),
                    Stat("Medal(s) - Gold", handlePlurals(stats.MedalGold, stats.MedalsGold)),
                    Stat("Medal(s) - Silver", handlePlurals(stats.MedalSilver, stats.MedalsSilver)),
                    Stat("Medal(s) - Bronze", handlePlurals(stats.MedalBronze, stats.MedalsBronze)),

                    // Game
                    StatHeader(categoryNames[6]),
                    //Stat("Game(s) Played", handlePlurals(stats.GamePlayed, stats.GamesPlayed)),
                    Stat("Game(s) Won", handlePlurals(stats.GameWon, stats.GamesWon)),
                    Stat("Time Spent on Fire", handlePlurals(stats.TimeSpentonFire, null)),
                    Stat("Objective Time", handlePlurals(stats.ObjectiveTime, null)),
                    Stat("Time Played", handlePlurals(stats.TimePlayed, null))
            )
        }
    }
}