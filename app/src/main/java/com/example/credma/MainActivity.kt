package com.example.credma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.credma.screens.AddCardScreen
import com.example.credma.screens.CardScreen
import com.example.credma.ui.theme.CredMaTheme
import com.example.credma.viewmodel.CardViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CredMaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewmodel: CardViewModel = viewModel(factory = viewModelFactory {
                        initializer {
                            val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CredMaApplication)
                            CardViewModel(app.cardRepository, app.transactionRepository)
                        }
                    })
                    NavHost(navController = navController, startDestination = "card") {
                        composable("card") { CardScreen(navController, viewmodel) }
                        composable("add_card") { AddCardScreen(viewmodel, navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CredMaTheme {
        Greeting("Android")
    }
}