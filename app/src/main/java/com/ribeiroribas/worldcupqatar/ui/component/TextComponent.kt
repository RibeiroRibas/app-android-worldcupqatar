package com.ribeiroribas.worldcupqatar.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Message(message: String) {
    Text(
        text = message,
        modifier = Modifier.padding(top = 64.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun Title(text: String) {
    Text(
        style = MaterialTheme.typography.h6,
        text = text,
        modifier = Modifier.padding(top = 8.dp)
    )
}