package com.assistiveapps.myapplication.di.module

import android.content.Context
import com.assistiveapps.myapplication.data.event.NoInternetEvent
import com.assistiveapps.myapplication.data.exception.NoConnectivityException
import com.assistiveapps.myapplication.di.qualifier.CacheInterceptor
import com.assistiveapps.myapplication.di.qualifier.HeaderInterceptor
import com.assistiveapps.myapplication.di.qualifier.NetworkInterceptor
import com.assistiveapps.myapplication.di.scope.NewsApplicationScope
import com.assistiveapps.myapplication.util.NetworkUtil
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

@Module(includes = [ContextModule::class])
class NetworkModule {

    companion object {
        private const val CONNECTION_TIMEOUT: Long = 60
        private const val WRITE_TIMEOUT: Long = 60
        private const val READ_TIMEOUT: Long = 60
        private const val MAX_STALE: Int = 7
        private const val MAX_AGE: Int = 0
        private const val CACHE_SIZE: Long = 10 * 1000 * 1000 //10 MB CACHE
        private const val CACHE_CONTROL = "Cache-Control"
    }

    @Provides
    @NewsApplicationScope
    fun okHttpClient(@HeaderInterceptor customInterceptor: Interceptor,
                     @NetworkInterceptor networkInterceptor: Interceptor,
                     @CacheInterceptor cacheInterceptor: Interceptor,
                     loggingInterceptor: HttpLoggingInterceptor,
                     cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(customInterceptor)
            .addInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(cacheInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @NewsApplicationScope
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.i(it)
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @NewsApplicationScope
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, CACHE_SIZE)
    }

    @Provides
    @NewsApplicationScope
    fun file(context: Context): File {
        return File(context.cacheDir, "okhttp-cache")
    }

    @Provides
    @NewsApplicationScope
    @HeaderInterceptor
    fun customInterceptor(): Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().build())
        }
    }

    @Provides
    @NewsApplicationScope
    @NetworkInterceptor
    fun networkInterceptor(networkUtil: NetworkUtil, cacheControl: CacheControl): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (!networkUtil.isOnline()) {
                EventBus.getDefault().post(NoInternetEvent())
                request = request.newBuilder().cacheControl(cacheControl).build()
                val response = chain.proceed(request)
                if (response.cacheResponse() == null)
                    throw NoConnectivityException()
            }
            return@Interceptor chain.proceed(request)
        }
    }

    @Provides
    @NewsApplicationScope
    fun cacheControl(): CacheControl {
        return CacheControl.Builder()
            .maxStale(MAX_STALE, TimeUnit.DAYS)
            .maxAge(MAX_AGE, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @NewsApplicationScope
    @CacheInterceptor
    fun cacheInterceptor(cacheControl: CacheControl): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build()
            return@Interceptor chain.proceed(request)
        }
    }
}