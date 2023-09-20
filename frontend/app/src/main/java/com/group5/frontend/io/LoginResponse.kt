package com.group5.frontend.io

import com.group5.frontend.model.User

data class LoginResponse(
    val token: String?,
    val message: String,
    val success: Boolean,
    val data: User?,
)