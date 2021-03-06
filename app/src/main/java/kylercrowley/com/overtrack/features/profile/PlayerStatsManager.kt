package kylercrowley.com.overtrack.features.profile

import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.api.LootboxPlayerAllHeroStats
import rx.Observable
import timber.log.Timber

class PlayerStatsManager(private val lootboxService: LootboxApiService, val platform: String, val region: String, val tag: String, val mode: String) {
    fun requestStats(): Observable<LootboxPlayerAllHeroStats?> {
        // Create a new Observable that will be executed once a Subscriber subscribes to it.
        return Observable.create {
            subscriber ->

            // getAllHeroStats will return a Call. Need to execute the call to get data back.
            val callResponse = lootboxService.getAllHeroStats(platform, region, tag, mode)
            val response = callResponse.execute()

            // If the response is successful, parsing can begin.
            if (response.isSuccessful) {
                // Get the list of stats (JSON Array of JSON Objects) from the response body.
                val stats = response.body()

                // Once the mapping is finished, the Observable will emit the List<LootboxPlayerAllHeroStats>
                // and complete.
                subscriber.onNext(stats)
                subscriber.onCompleted()
            } else {
                // If there is an error, create a new Throwable will the response message.
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}