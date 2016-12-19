package kylercrowley.com.overtrack.di

import android.content.Context
import dagger.Module
import dagger.Provides
import kylercrowley.com.overtrack.CACHE_FILE_NAME
import kylercrowley.com.overtrack.CACHE_FILE_SIZE
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

@Module(includes = arrayOf(ApplicationModule::class))
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            message -> Timber.i(message)
        })

        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        return interceptor
    }

    @Provides
    @ApplicationScope
    fun provideCacheFile(context: Context): File {
        val cacheDir = File(context.cacheDir, CACHE_FILE_NAME)
        cacheDir.mkdirs()

        return cacheDir
    }

    @Provides
    @ApplicationScope
    fun provideCache(cacheDir: File): Cache = Cache(cacheDir, CACHE_FILE_SIZE)

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build()
    }
}