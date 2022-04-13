package dev.ferrant.quickcoin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.ferrant.quickcoin.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                QuickCoinApp()
            }
        }
    }
}
