package com.example.newsappjetpackcompose.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BrandBlue,
    onPrimary = TextPrimaryDark,
    primaryContainer = Blue2,
    onPrimaryContainer = Blue7,
    secondary = BrandBlueHighlight,
    onSecondary = TextLinkDark,
    tertiary = SystemInformative,
    background = BackgroundPrimaryDark,
    onBackground = TextPrimaryDark,
    surface = BackgroundSecondaryDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = Grey7,
    onSurfaceVariant = TextSecondaryDark,
    error = SystemError,
    onError = TextPrimaryDark
)

private val LightColorScheme = lightColorScheme(
    primary = BrandBlue,
    onPrimary = TextPrimaryDark,
    primaryContainer = BrandBlueHighlight,
    onPrimaryContainer = TextLinkLight,
    secondary = Grey2,
    onSecondary = TextPrimaryLight,
    tertiary = SystemInformative,
    background = BackgroundPrimaryLight,
    onBackground = TextPrimaryLight,
    surface = BackgroundSecondaryLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = Grey1,
    onSurfaceVariant = TextSecondaryLight,
    error = SystemError,
    onError = TextPrimaryDark
)

@Composable
fun NewsAppJetpackComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(LocalNewsTypography provides NewsAppTypography) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
