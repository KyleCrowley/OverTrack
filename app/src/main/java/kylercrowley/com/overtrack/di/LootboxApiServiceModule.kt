package kylercrowley.com.overtrack.di

import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.api.LOOTBOX_API_BASE_URL
import kylercrowley.com.overtrack.api.LootboxApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = arrayOf(NetworkModule::class))
class LootboxApiServiceModule {

    @Provides
    @ApplicationScope
    fun provideLootboxApiService(retrofit: Retrofit): LootboxApiService = retrofit.create(LootboxApiService::class.java)

    @Provides
    @ApplicationScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(LOOTBOX_API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}