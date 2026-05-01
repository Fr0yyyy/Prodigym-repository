package com.example.prodigym.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prodigym.ui.theme.ProdiGymTheme

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    SplashScreenContent(
        uiState = uiState,
        onNavigateToLogin = onNavigateToLogin,
        modifier = modifier
    )
}

@Composable
private fun SplashScreenContent(
    uiState: SplashUiState,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(uiState) {
        if (uiState is SplashUiState.NavigateToLogin) {
            onNavigateToLogin()
        }
    }

    val alpha = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        alpha.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 800))
    }

    val background = MaterialTheme.colorScheme.background

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            background.copy(alpha = 0.6f),
                            background
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SplashBrandingSection()
            Spacer(modifier = Modifier.weight(1f))
            SplashTaglineSection()
            Spacer(modifier = Modifier.weight(1f))
            SplashLoadingSection(isLoading = uiState is SplashUiState.Loading)
        }
    }
}

@Composable
private fun SplashBrandingSection(modifier: Modifier = Modifier) {
    val primary = MaterialTheme.colorScheme.primary
    val onBackground = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ELITE HUMAN PERFORMANCE",
            style = MaterialTheme.typography.labelMedium,
            color = primary
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = onBackground)) { append("PRODI") }
                withStyle(SpanStyle(color = primary)) { append("GYM") }
            },
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Composable
private fun SplashTaglineSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Normal)) { append("UNLEASH YOUR ") }
                withStyle(SpanStyle(fontWeight = FontWeight.Black)) { append("PRIME") }
                withStyle(SpanStyle(fontWeight = FontWeight.Normal)) { append("\nPOTENTIAL") }
            },
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SplashLoadingSection(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = primary,
                strokeWidth = 2.dp
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .background(
                            color = if (index == 0) primary else secondary,
                            shape = CircleShape
                        )
                )
            }
        }

        Text(
            text = "SYNCHRONIZING BIO-DATA",
            style = MaterialTheme.typography.labelSmall,
            color = secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    ProdiGymTheme {
        SplashScreenContent(
            uiState = SplashUiState.Loading,
            onNavigateToLogin = {}
        )
    }
}
