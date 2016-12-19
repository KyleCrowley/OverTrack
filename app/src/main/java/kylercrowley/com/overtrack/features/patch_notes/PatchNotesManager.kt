package kylercrowley.com.overtrack.features.patch_notes

import kylercrowley.com.overtrack.api.LootboxApiService
import kylercrowley.com.overtrack.api.LootboxPatchNote
import rx.Observable

// TODO: LootboxApiService is being passed in constructor. Use @Inject?
class PatchNotesManager(private val lootboxService: LootboxApiService) {
    fun requestPatchNotes(): Observable<List<LootboxPatchNote>> {
        // Create a new Observable that will be executed once a Subscriber subscribes to it.
        return Observable.create {
            subscriber ->

            // getPatchNotes will return a Call. Need to execute the call to get data back.
            val callResponse = lootboxService.getPatchNotes()
            val response = callResponse.execute()

            // If the response is successful, parsing can begin.
            if (response.isSuccessful) {
                // Get the list of patch notes from the response body.
                val patchNotes = response.body().patchNotes

                // Once the mapping is finished, the Observable will emit the List<PatchNote>
                // and complete.
                subscriber.onNext(patchNotes)
                subscriber.onCompleted()
            } else {
                // If there is an error, create a new Throwable with the response message.
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}