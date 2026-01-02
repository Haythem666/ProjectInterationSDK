package com.example.projectintertionsdk.sdk.PasswordStrengthMeter.core

enum class PasswordStrength(val score: Int, val label: String) {
    VERY_WEAK(0,"Very Weak"),
    WEAK(1,"Weak"),
    MEDIUM(2,"Medium"),
    STRONG(3,"Strong"),
    VERY_STRONG(4,"Very Strong")
}