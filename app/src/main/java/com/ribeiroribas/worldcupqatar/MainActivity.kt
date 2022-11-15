package com.ribeiroribas.worldcupqatar


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ribeiroribas.worldcupqatar.ui.navigation.WorldCupQatarApp
import com.ribeiroribas.worldcupqatar.ui.theme.WorldCupQatarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }
        setContent {
            WorldCupQatarTheme {
                window.statusBarColor = MaterialTheme.colors.background.copy(alpha = 0.9f).toArgb()
                WorldCupQatarApp()
            }
        }
    }
}
