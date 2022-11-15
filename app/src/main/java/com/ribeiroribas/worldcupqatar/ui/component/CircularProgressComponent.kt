package com.ribeiroribas.worldcupqatar.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgress(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize().height(200.dp),
    contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            color = Color.White,
            modifier = modifier.size(50.dp)
        )
    }
}