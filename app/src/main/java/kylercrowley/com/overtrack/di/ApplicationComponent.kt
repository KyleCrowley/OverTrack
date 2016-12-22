package kylercrowley.com.overtrack.di

import com.squareup.picasso.Picasso
import dagger.Component
import kylercrowley.com.overtrack.api.LootboxApiService

@ApplicationScope
@Component(modules = arrayOf(LootboxApiServiceModule::class, PicassoModule::class))
interface ApplicationComponent {

    fun getLootboxApiService(): LootboxApiService

    fun getPicasso(): Picasso
}