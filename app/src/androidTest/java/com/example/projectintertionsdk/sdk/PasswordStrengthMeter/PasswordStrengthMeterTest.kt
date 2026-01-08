package com.example.projectintertionsdk.sdk.PasswordStrengthMeter

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core.DefaultPasswordStrengthAlgorithm
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core.PasswordStrength
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.visualizers.BarVisualizer
import org.junit.Rule
import org.junit.Test
import androidx.compose.runtime.mutableStateOf

class PasswordStrengthMeterTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun emptyPassword(){
        composeTestRule.setContent {
            PasswordStrengthMeter(password = "", visualizer = BarVisualizer(showLabel = true))
        }

        composeTestRule.onNodeWithText("Very Weak").assertDoesNotExist()
    }

    @Test
    fun weakPassword(){
        composeTestRule.setContent {
            PasswordStrengthMeter(
                password = "weak1234",
                visualizer = BarVisualizer(showLabel = true)
            )
        }

        composeTestRule.onNodeWithText("Weak").assertIsDisplayed()
    }

    @Test
    fun mediumPassword(){
        composeTestRule.setContent {
            PasswordStrengthMeter(
                password = "Abcd1234",
                visualizer = BarVisualizer(showLabel = true)
            )
        }

        composeTestRule.onNodeWithText("Medium").assertIsDisplayed()
    }

    @Test
    fun strongPassword(){
        composeTestRule.setContent {
            PasswordStrengthMeter(
                password = "Abcd1234!",
                visualizer = BarVisualizer(showLabel = true)
            )
        }

        composeTestRule.onNodeWithText("Strong").assertIsDisplayed()
    }

    @Test
    fun veryStrongPassword(){
        composeTestRule.setContent {
            PasswordStrengthMeter(
                password = "Password12345??",
                visualizer = BarVisualizer(showLabel = true)
            )
        }

        composeTestRule.onNodeWithText("Very Strong").assertIsDisplayed()
    }

    @Test
    fun passwordChange(){

        var passwordState = mutableStateOf("weak")

        composeTestRule.setContent {
            PasswordStrengthMeter(
                password = passwordState.value,
                visualizer = BarVisualizer(showLabel = true)
            )
        }

        composeTestRule.onNodeWithText("Very Weak").assertIsDisplayed()

        passwordState.value = "StrongPassword12?"

        composeTestRule.onNodeWithText("Very Strong").assertIsDisplayed()
    }

    @Test
    fun evaluateLength(){
        val algo = DefaultPasswordStrengthAlgorithm()


        val shortPassword= algo.calculate("abc123")
        assert(shortPassword == PasswordStrength.VERY_WEAK || shortPassword == PasswordStrength.WEAK)

        val mediumPassword = algo.calculate("Abcd1234")
        assert(mediumPassword.score >= 2)

        val longPassword = algo.calculate("Abcd1234!@#$")
        assert(longPassword.score >= 4)
    }

    @Test
    fun evaluatesCharacterDiversity() {
        val algorithm = DefaultPasswordStrengthAlgorithm()

        val onlyLower = algorithm.calculate("abcdefgh")
        assert(onlyLower.score == 0)

        val lowerUpper = algorithm.calculate("AbcdEfgh")
        assert(lowerUpper.score == 1)

        val withDigits = algorithm.calculate("Abcd1234")
        assert(withDigits.score == 2)

        val allTypes = algorithm.calculate("Abcd123!")
        assert(allTypes.score == 3)

        val longEnough = algorithm.calculate("Abcd12345!sa")
        assert(longEnough.score == 4)


    }


}