package com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core

/**
 * Defines the contract for a password strength calculation algorithm.
 */
interface PasswordStrengthAlgorithm{
    /**
     * Calculates the strength of a given password.
     *
     * @param password The password to be evaluated.
     * @return The calculated [PasswordStrength].
     */
    fun calculate(password: String): PasswordStrength
}