package kylercrowley.com.overtrack.features.profile

import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.api.LootboxPlayerProfile
import rx.Observable

class ProfileManager(private val lootboxService: LootboxApiService, val platform: String, val region: String, val tag: String) {
    fun requestProfile(): Observable<LootboxPlayerProfile?> {
        // Create a new Observable that will be executed once a Subscriber subscribes to it.
        return Observable.create {
            subscriber ->

            // getPlayerProfile will return a Call. Need to execute the call to get data back.
            val callResponse = lootboxService.getPlayerProfile(platform, region, tag)
            val response = callResponse.execute()

            // If the response is successful, parsing can begin.
            if (response.isSuccessful) {
                // Get the list of patch notes (JSON Array of JSON Objects) from the response body.
                val profile = response.body().data

                // Once the mapping is finished, the Observable will emit the List<PatchNote>
                // and complete.
                subscriber.onNext(profile)
                subscriber.onCompleted()
            } else {
                // If there is an error, create a new Throwable will the response message.
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}