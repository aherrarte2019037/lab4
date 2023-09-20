package com.group5.frontend.io

data class RegisterRequest(
    val email: String,
    val password: String,
    val firstname: String,
    val lastname: String,
)