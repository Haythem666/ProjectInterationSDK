package com.example.projectintertionsdk.sdk.PasswordStrengthMeter

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core.DefaultPasswordStrengthAlgorithm
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core.PasswordStrengthAlgorithm
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.visualizers.BarVisualizer
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.visualizers.PasswordStrengthVisualizer


@Composable
fun PasswordStrengthMeter(
    password: String,
    modifier: Modifier= Modifier,
    algorithm: PasswordStrengthAlgorithm = DefaultPasswordStrengthAlgorithm(),
    visualizer: PasswordStrengthVisualizer = BarVisualizer()
){
    val strength = remember(password) {
        algorithm.calculate(password)
    }

    Column(modifier = modifier) {
        visualizer.Visualize(strength = strength, password = password)
    }
}

