package com.ribeiroribas.worldcupqatar


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import com.ribeiroribas.worldcupqatar.ui.navigation.WorldCupQatarApp
import com.ribeiroribas.worldcupqatar.ui.theme.WorldCupQatarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorldCupQatarTheme {
                window.statusBarColor = MaterialTheme.colors.background.copy(alpha = 0.9f).toArgb()
                WorldCupQatarApp()
            }
        }
    }
}
