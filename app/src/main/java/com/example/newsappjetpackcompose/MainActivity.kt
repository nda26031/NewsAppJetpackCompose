package com.example.newsappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.newsappjetpackcompose.ui.demo.OverlayDemoScreen
import com.example.newsappjetpackcompose.ui.theme.NewsAppJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppJetpackComposeTheme {
                OverlayDemoScreen()
            }
        }
    }
}
