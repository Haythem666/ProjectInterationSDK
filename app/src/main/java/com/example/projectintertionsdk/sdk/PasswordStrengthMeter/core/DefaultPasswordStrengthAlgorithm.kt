package com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core

/**
 * A default implementation of the [PasswordStrengthAlgorithm].
 * It calculates the password strength based on a scoring system.
 */
class DefaultPasswordStrengthAlgorithm: PasswordStrengthAlgorithm{

    /**
     * Calculates the strength of a given password.
     *
     * The score is determined by the following criteria:
     * - Length: +2 for >= 12 chars, +1 for >= 8 chars.
     * - Character types: +1 for each of lowercase, uppercase, digit, and special character.
     *
     * @param password The password to evaluate.
     * @return The calculated [PasswordStrength].
     */
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
