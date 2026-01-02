package com.example.projectintertionsdk.sdk.PasswordStrengthMeter.visualizers

import androidx.compose.runtime.Composable
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core.PasswordStrength

interface PasswordStrengthVisualizer {
    @Composable
    fun Visualize(strength: PasswordStrength, password: String)
}