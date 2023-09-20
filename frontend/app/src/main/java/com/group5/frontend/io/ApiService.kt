package com.group5.frontend.io

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login.php")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register.php")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}