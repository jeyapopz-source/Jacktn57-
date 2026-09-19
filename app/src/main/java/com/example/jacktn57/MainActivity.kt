package com.example.jacktn57

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jacktn57.ui.JackViewModel
import com.example.jacktn57.ui.screens.*
import com.example.jacktn57.ui.theme.*

class MainActivity : ComponentActivity() {
    private val viewModel: JackViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            JackTN57Theme {
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                var currentTab by remember { mutableIntStateOf(0) }
                val context = LocalContext.current

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = Amber500
                                    ) {
                                        Text(
                                            text = "TN-57",
                                            fontWeight = FontWeight.Black,
                                            fontSize = 13.sp,
                                            color = Navy900,
                                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = "Jack TN57",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "திண்டுக்கல் வாகன வழிகாட்டி",
                                            fontSize = 11.sp,
                                            color = Amber100
                                        )
                                    }
                                }
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:1033"))
                                        context.startActivity(dialIntent)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PhoneInTalk,
                                        contentDescription = "Emergency SOS 1033",
                                        tint = Amber500
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Navy900,
                                titleContentColor = Color.White
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = Navy900,
                            contentColor = Color.White
                        ) {
                            NavigationBarItem(
                                selected = currentTab == 0,
                                onClick = { currentTab = 0 },
                                icon = {
                                    Icon(
                                        if (currentTab == 0) Icons.Filled.Home else Icons.Outlined.Home,
                                        contentDescription = "Home"
                                    )
                                },
                                label = { Text("Home", fontSize = 11.sp) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Navy900,
                                    selectedTextColor = Amber500,
                                    indicatorColor = Amber500,
                                    unselectedIconColor = Color(0xFF94A3B8),
                                    unselectedTextColor = Color(0xFF94A3B8)
                                )
                            )
                            NavigationBarItem(
                                selected = currentTab == 1,
                                onClick = { currentTab = 1 },
                                icon = {
                                    Icon(
                                        if (currentTab == 1) Icons.Filled.Build else Icons.Outlined.Build,
                                        contentDescription = "Jack & Tools"
                                    )
                                },
                                label = { Text("Tools", fontSize = 11.sp) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Navy900,
                                    selectedTextColor = Amber500,
                                    indicatorColor = Amber500,
                                    unselectedIconColor = Color(0xFF94A3B8),
                                    unselectedTextColor = Color(0xFF94A3B8)
                                )
                            )
                            NavigationBarItem(
                                selected = currentTab == 2,
                                onClick = { currentTab = 2 },
                                icon = {
                                    Icon(
                                        if (currentTab == 2) Icons.Filled.AccountBalance else Icons.Outlined.AccountBalance,
                                        contentDescription = "RTO & Fines"
                                    )
                                },
                                label = { Text("RTO", fontSize = 11.sp) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Navy900,
                                    selectedTextColor = Amber500,
                                    indicatorColor = Amber500,
                                    unselectedIconColor = Color(0xFF94A3B8),
                                    unselectedTextColor = Color(0xFF94A3B8)
                                )
                            )
                            NavigationBarItem(
                                selected = currentTab == 3,
                                onClick = { currentTab = 3 },
                                icon = {
                                    Icon(
                                        if (currentTab == 3) Icons.Filled.DirectionsCar else Icons.Outlined.DirectionsCar,
                                        contentDescription = "Garage"
                                    )
                                },
                                label = { Text("Garage", fontSize = 11.sp) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Navy900,
                                    selectedTextColor = Amber500,
                                    indicatorColor = Amber500,
                                    unselectedIconColor = Color(0xFF94A3B8),
                                    unselectedTextColor = Color(0xFF94A3B8)
                                )
                            )
                            NavigationBarItem(
                                selected = currentTab == 4,
                                onClick = { currentTab = 4 },
                                icon = {
                                    Icon(
                                        if (currentTab == 4) Icons.Filled.LocalGasStation else Icons.Outlined.LocalGasStation,
                                        contentDescription = "Fuel Tracker"
                                    )
                                },
                                label = { Text("Fuel", fontSize = 11.sp) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Navy900,
                                    selectedTextColor = Amber500,
                                    indicatorColor = Amber500,
                                    unselectedIconColor = Color(0xFF94A3B8),
                                    unselectedTextColor = Color(0xFF94A3B8)
                                )
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (currentTab) {
                            0 -> HomeScreen(
                                state = state,
                                onNavigateToTab = { currentTab = it },
                                onSelectVehicle = { viewModel.selectVehicle(it) }
                            )
                            1 -> RoadsideToolsScreen(
                                state = state,
                                onToggleKitItem = { viewModel.toggleKitItem(it) }
                            )
                            2 -> RtoDirectoryScreen()
                            3 -> GarageScreen(
                                state = state,
                                onSelectVehicle = { viewModel.selectVehicle(it) },
                                onAddVehicle = { reg, model, type, fuel, ins, puc, odo, policy ->
                                    viewModel.addVehicle(reg, model, type, fuel, ins, puc, odo, policy)
                                }
                            )
                            4 -> FuelTrackerScreen(
                                state = state,
                                onAddFuelLog = { odo, liters, cost, station, fullTank ->
                                    viewModel.addFuelLog(odo, liters, cost, station, fullTank)
                                }
                            )
                            5 -> QuizScreen(
                                quizState = state.quizState,
                                onAnswer = { viewModel.answerQuiz(it) },
                                onNext = { viewModel.nextQuestion() },
                                onRestart = { viewModel.restartQuiz() }
                            )
                        }
                    }
                }
            }
        }
    }
}
