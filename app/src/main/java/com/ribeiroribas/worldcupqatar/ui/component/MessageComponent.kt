package com.ribeiroribas.worldcupqatar.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Message(
    modifier: Modifier = Modifier,
    message: String
) {
    Text(
        text = message,
        modifier = modifier.padding(top = 64.dp),
        textAlign = TextAlign.Center
    )
}