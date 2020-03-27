package br.com.tecapp.mavelapp.shared.service.interceptors

import br.com.tecapp.mavelapp.shared.utils.SharedPreferencesHelper
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(private val preferences: SharedPreferencesHelper) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = preferences[SharedPreferencesHelper.AUTH_TOKEN, ""]
        if (token.isNullOrEmpty()) {
            val response = chain.proceed(chain.request())
            val tokenResponse = response.headers().get("Authorization") ?: ""
            preferences.put(SharedPreferencesHelper.AUTH_TOKEN, tokenResponse)

            return response
        }

        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Authorization", token)
            .build()

        val response = chain.proceed(request)

        val tokenResponse = response.headers().get("Authorization") ?: ""
        preferences.put(SharedPreferencesHelper.AUTH_TOKEN, tokenResponse)

        return response
    }

}