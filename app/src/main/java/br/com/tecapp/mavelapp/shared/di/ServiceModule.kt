package br.com.tecapp.mavelapp.shared.di

import android.content.Context
import br.com.tecapp.mavelapp.BuildConfig
import br.com.tecapp.mavelapp.R
import br.com.tecapp.mavelapp.shared.service.HeroService
import br.com.tecapp.mavelapp.shared.service.interceptors.AuthInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val serviceModule = module {
    single { AuthInterceptor(get()) }
    factory { provideOkHttpClient() }
    factory { provideGson() }
    single { provideRetrofit(androidContext(), get(), get(), get()) }

    factory { provideHeroService(get()) }
}

fun provideRetrofit(
    context: Context,
    client: OkHttpClient.Builder,
    gson: Gson,
    authInterceptor: AuthInterceptor
): Retrofit {
    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logging)
    }

    client.addInterceptor(authInterceptor)

    return Retrofit.Builder()
        .baseUrl(context.getString(R.string.API_URL))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client.build())
        .build()
}

private const val timeout: Long = 10
private fun provideOkHttpClient(): OkHttpClient.Builder {
    return OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .writeTimeout(timeout, TimeUnit.SECONDS)
}

private fun provideGson() = GsonBuilder().create()

private fun provideHeroService(retrofit: Retrofit): HeroService =
    retrofit.create(HeroService::class.java)


