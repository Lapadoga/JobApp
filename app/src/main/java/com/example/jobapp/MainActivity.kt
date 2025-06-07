package com.example.jobapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.jobapp.ui.bottomBar.BottomBarScreen
import com.example.jobapp.ui.navigation.Navigation
import com.example.jobapp.ui.theme.JobAppTheme
import com.example.jobapp.ui.vacanciesLists.VacanciesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JobAppTheme {
                val sharedViewModel: VacanciesViewModel = hiltViewModel()
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomBarScreen(navController, sharedViewModel)
                    }
                ) { paddingValues ->
                    Navigation(navController, paddingValues, sharedViewModel)
                }
            }
        }
    }
}