package com.example.projectintertionsdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.test.PasswordStrengthTestApp
import com.example.projectintertionsdk.sdk.ShoppingCart.test.ShoppingCartTestApp
import com.example.projectintertionsdk.ui.theme.ProjectIntertionSDKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectIntertionSDKTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //PasswordStrengthTestApp()
                    ShoppingCartTestApp()
                }
            }
        }
    }
}

