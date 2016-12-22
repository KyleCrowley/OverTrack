package kylercrowley.com.overtrack.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LootboxApiService {

    @GET("/patch_notes")
    fun getPatchNotes(): Call<LootboxPatchNotesResponse>

    @GET("/{platform}/{region}/{tag}/achievements")
    fun getPlayerAchievements(
            @Path("platform") platform: String,
            @Path("region") region: String,
            @Path("tag") tag: String
    ): Call<LootboxPlayerAchievementsResponse>

    @GET("/{platform}/{region}/{tag}/profile")
    fun getPlayerProfile(
            @Path("platform") platform: String,
            @Path("region") region: String,
            @Path("tag") tag: String
    ): Call<LootboxPlayerProfileResponse>

    @GET("/{platform}/{region}/{tag}/{mode}/allHeroes/")
    fun getAllHeroStats(
            @Path("platform") platform: String,
            @Path("region") region: String,
            @Path("tag") tag: String,
            @Path("mode") mode: String
    ): Call<LootboxPlayerAllHeroStats>
}