package kylercrowley.com.overtrack.di

import dagger.Component
import kylercrowley.com.overtrack.api.LootboxApiService

@ApplicationScope
@Component(modules = arrayOf(LootboxApiServiceModule::class))
interface ApplicationComponent {

    fun getLootboxApiService(): LootboxApiService
}