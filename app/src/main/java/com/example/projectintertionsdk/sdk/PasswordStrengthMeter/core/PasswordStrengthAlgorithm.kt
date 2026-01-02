package com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core

interface PasswordStrengthAlgorithm{
    fun calculate(password: String): PasswordStrength
}