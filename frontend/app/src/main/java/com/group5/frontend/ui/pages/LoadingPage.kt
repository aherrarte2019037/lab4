package com.group5.frontend.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(alignment = Alignment.Center),
            color = Color.Blue
        )
    }
}