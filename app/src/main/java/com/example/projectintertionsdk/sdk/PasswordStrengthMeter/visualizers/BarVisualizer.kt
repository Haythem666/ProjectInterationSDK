package com.example.projectintertionsdk.sdk.PasswordStrengthMeter.visualizers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core.PasswordStrength

class BarVisualizer(
    private val height: Dp = 8.dp,
    private val showLabel: Boolean = true
) : PasswordStrengthVisualizer {

    @Composable
    override fun Visualize(strength: PasswordStrength, password: String) {
        Column(modifier = Modifier.fillMaxWidth()) {
            //barre de progression
            LinearProgressIndicator(
                progress = {strength.score/4f},
                modifier = Modifier.fillMaxWidth().height(height).clip(RoundedCornerShape(4.dp)),
                color = getColorForStrength(strength),
                trackColor = Color.LightGray.copy(alpha = 0.3f)
            )

            if(showLabel && password.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = strength.label,
                    style = MaterialTheme.typography.bodySmall,
                    color = getColorForStrength(strength)
                )
            }

        }
    }

    private fun getColorForStrength(strength: PasswordStrength): Color{
        return when (strength) {
            PasswordStrength.VERY_WEAK -> Color(0xFFD32F2F)
            PasswordStrength.WEAK -> Color(0xFFF57C00)
            PasswordStrength.MEDIUM -> Color(0xFFFBC02D)
            PasswordStrength.STRONG -> Color(0xFF7CB342)
            PasswordStrength.VERY_STRONG -> Color(0xFF388E3C)
        }
    }
}
