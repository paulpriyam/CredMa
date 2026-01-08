package com.example.credma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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

import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.credma.components.BottomNavigationBar
import com.example.credma.navigation.BottomNavItem
import com.example.credma.screens.AllCardsScreen
import com.example.credma.screens.AllTransactionsScreen
import com.example.credma.screens.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CredMaTheme {
                val navController = rememberNavController()
                val viewmodel: CardViewModel = viewModel(factory = viewModelFactory {
                    initializer {
                        val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CredMaApplication)
                        CardViewModel(app.cardRepository, app.transactionRepository)
                    }
                })

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val showBottomBar = currentRoute in listOf(
                    BottomNavItem.Card.route,
                    BottomNavItem.AllCards.route,
                    BottomNavItem.AllTransactions.route,
                    BottomNavItem.Settings.route
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController, 
                        startDestination = BottomNavItem.Card.route,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(BottomNavItem.Card.route) { CardScreen(navController, viewmodel) }
                        composable(BottomNavItem.AllCards.route) { AllCardsScreen() }
                        composable(BottomNavItem.AllTransactions.route) { AllTransactionsScreen() }
                        composable(BottomNavItem.Settings.route) { SettingsScreen() }
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