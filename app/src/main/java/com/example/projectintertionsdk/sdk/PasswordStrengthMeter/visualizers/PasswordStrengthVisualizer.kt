package com.example.projectintertionsdk.sdk.PasswordStrengthMeter.visualizers

import androidx.compose.runtime.Composable
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core.PasswordStrength

/**
 * An interface for visualizing the strength of a password.
 *
 * Implement this interface to create a custom visual representation of password strength.
 */
interface PasswordStrengthVisualizer {

    /**
     * A Composable function that visualizes the password strength.
     *
     * @param strength The calculated password strength.
     * @param password The password being evaluated.
     */
    @Composable
    fun Visualize(strength: PasswordStrength, password: String)
}