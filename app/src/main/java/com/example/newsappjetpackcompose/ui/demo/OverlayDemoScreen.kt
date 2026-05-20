package com.example.newsappjetpackcompose.ui.demo

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsappjetpackcompose.ui.theme.NewsAppJetpackComposeTheme

private val OverlayColor = Color(0x33160F22)

@Composable
fun OverlayDemoScreen(modifier: Modifier = Modifier) {
    var isOverlayVisible by rememberSaveable { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF7F5FA))
    ) {
        Crossfade(
            targetState = isOverlayVisible,
            label = "OverlayDemoBlur"
        ) { visible ->
            DemoContent(
                onShowOverlay = { isOverlayVisible = true },
                modifier = Modifier.blur(if (visible) 4.dp else 0.dp)
            )
        }

        if (isOverlayVisible) {
            BinoOverlay(
                modifier = Modifier.clickable { isOverlayVisible = false }
            )
        }
    }
}

@Composable
fun BinoOverlay(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OverlayColor)
    )
}

@Composable
private fun DemoContent(onShowOverlay: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 28.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "BINO",
            color = Color(0xFF160F22),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold
        )

        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White,
            tonalElevation = 1.dp,
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF6C5CE7))
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Tin moi hom nay",
                            color = Color(0xFF160F22),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "8 phut truoc",
                            color = Color(0xFF7A7483),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                Text(
                    text = "Noi dung demo phia sau overlay duoc lam mo de mo phong backdrop blur 4dp.",
                    color = Color(0xFF443B4F),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFFEDE9F7),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Overlay",
                    color = Color(0xFF160F22),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Mau phu #160F22 voi alpha 20%, full man hinh.",
                    color = Color(0xFF5B5266),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onShowOverlay,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF160F22),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(text = "Hien thi overlay")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OverlayDemoScreenPreview() {
    NewsAppJetpackComposeTheme {
        OverlayDemoScreen()
    }
}
