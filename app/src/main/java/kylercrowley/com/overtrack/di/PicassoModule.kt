package kylercrowley.com.overtrack.di

import android.content.Context
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.di.scopes.ApplicationScope
import okhttp3.OkHttpClient

@Module(includes = arrayOf(ApplicationModule::class, NetworkModule::class))
class PicassoModule {

    @Provides
    @ApplicationScope
    fun providePicasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build()
    }

    @Provides
    @ApplicationScope
    fun provideOkHttp3Download(okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }
}