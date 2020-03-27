package br.com.tecapp.mavelapp.shared.service

import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface HeroService {
    @POST("/signin")
    fun signIn(@Body user: String): Completable
}