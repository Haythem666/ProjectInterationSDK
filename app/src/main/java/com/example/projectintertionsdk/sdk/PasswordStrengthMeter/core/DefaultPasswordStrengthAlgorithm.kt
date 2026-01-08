package com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core

class DefaultPasswordStrengthAlgorithm: PasswordStrengthAlgorithm{
    override fun calculate(password: String): PasswordStrength {
        if (password.isEmpty()) return PasswordStrength.VERY_WEAK

        var score = 0

        when {
            password.length >= 12 -> score += 2
            password.length >= 8 -> score += 1
        }

        if (password.any {it.isLowerCase()}) score ++
        if (password.any {it.isUpperCase()}) score ++
        if (password.any { it.isDigit() }) score ++
        if (password.any { !it.isLetterOrDigit() }) score ++

        return when {
            score <= 2 -> PasswordStrength.VERY_WEAK
            score == 3 -> PasswordStrength.WEAK
            score == 4 -> PasswordStrength.MEDIUM
            score == 5 -> PasswordStrength.STRONG
            else -> PasswordStrength.VERY_STRONG
        }
    }
}