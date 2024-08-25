package com.atilsamancioglu.cryptocrazycompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import com.atilsamancioglu.cryptocrazycompose.ui.theme.CryptoCrazyComposeTheme
import com.atilsamancioglu.cryptocrazycompose.view.HomePage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCrazyComposeTheme {
                Scaffold {
                    HomePage()
                }
            }
        }
    }
}


