package com.example.newsappjetpackcompose.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.newsappjetpackcompose.R

val InterFontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)

@Immutable
data class NewsTypography(
    val h1: TextStyle = TextStyle.Default,
    val h2: TextStyle = TextStyle.Default,
    val h3: TextStyle = TextStyle.Default,
    val h4: TextStyle = TextStyle.Default,
    val h5: TextStyle = TextStyle.Default,
    val body1SemiBold: TextStyle = TextStyle.Default,
    val body1Regular: TextStyle = TextStyle.Default,
    val body2SemiBold: TextStyle = TextStyle.Default,
    val body2Regular: TextStyle = TextStyle.Default,
    val button1: TextStyle = TextStyle.Default,
    val button2: TextStyle = TextStyle.Default,
    val footnoteSemiBold: TextStyle = TextStyle.Default,
    val footnoteRegular: TextStyle = TextStyle.Default
)

val NewsAppTypography = NewsTypography(
    h1 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 56.sp
    ),
    h2 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 48.sp
    ),
    h3 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    h4 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    h5 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    body1SemiBold = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    body1Regular = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    body2SemiBold = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),
    body2Regular = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),
    button1 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    button2 = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),
    footnoteSemiBold = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    footnoteRegular = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )
)

val LocalNewsTypography = staticCompositionLocalOf { NewsTypography() }

val MaterialTheme.newsTypography: NewsTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalNewsTypography.current

val Typography = Typography(
    displayLarge = NewsAppTypography.h1,
    displayMedium = NewsAppTypography.h2,
    headlineLarge = NewsAppTypography.h3,
    headlineMedium = NewsAppTypography.h4,
    headlineSmall = NewsAppTypography.h5,
    titleMedium = NewsAppTypography.body2SemiBold,
    bodyLarge = NewsAppTypography.body1Regular,
    bodyMedium = NewsAppTypography.body2Regular,
    labelLarge = NewsAppTypography.button1,
    labelMedium = NewsAppTypography.button2,
    labelSmall = NewsAppTypography.footnoteRegular
)
