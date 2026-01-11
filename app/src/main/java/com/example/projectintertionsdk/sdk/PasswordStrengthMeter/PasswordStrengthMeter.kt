package com.example.projectintertionsdk.sdk.PasswordStrengthMeter

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core.DefaultPasswordStrengthAlgorithm
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core.PasswordStrengthAlgorithm
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.visualizers.BarVisualizer
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.visualizers.PasswordStrengthVisualizer

/**
 * A Composable that displays the strength of a given password.
 *
 * This component uses a [PasswordStrengthAlgorithm] to calculate the strength
 * and a [PasswordStrengthVisualizer] to display it.
 *
 * @param password The password to evaluate.
 * @param modifier The modifier to be applied to the layout.
 * @param algorithm The algorithm used to calculate the password strength.
 * @param visualizer The visualizer used to display the password strength.
 */
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

