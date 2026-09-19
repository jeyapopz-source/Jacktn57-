package com.example.jacktn57.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jacktn57.model.FuelLog
import com.example.jacktn57.model.ServiceRecord
import com.example.jacktn57.ui.JackUiState
import com.example.jacktn57.ui.theme.*

@Composable
fun FuelTrackerScreen(
    state: JackUiState,
    onAddFuelLog: (odometer: Int, liters: Double, cost: Double, station: String, isFullTank: Boolean) -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedSubTab by remember { mutableIntStateOf(0) }

    Scaffold(
        floatingActionButton = {
            if (selectedSubTab == 0) {
                FloatingActionButton(
                    onClick = { showAddDialog = true },
                    containerColor = Amber500,
                    contentColor = Navy900
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Refill")
                        Text("Add Refill", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Stats summary card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Navy800),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "VEHICLE EFFICIENCY OVERVIEW",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Amber500,
                        letterSpacing = 0.8.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "${state.averageMileage} km/L",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                            Text(
                                text = "Avg Calculated Economy",
                                fontSize = 12.sp,
                                color = Color(0xFFCBD5E1)
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "₹${state.totalFuelSpent.toInt()}",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Amber100
                            )
                            Text(
                                text = "Total Logged Spend",
                                fontSize = 12.sp,
                                color = Color(0xFFCBD5E1)
                            )
                        }
                    }
                }
            }

            // Sub Tab Row
            TabRow(
                selectedTabIndex = selectedSubTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = Navy700
            ) {
                Tab(
                    selected = selectedSubTab == 0,
                    onClick = { selectedSubTab = 0 },
                    text = { Text("Fuel Refill Logs (${state.activeFuelLogs.size})", fontWeight = FontWeight.Bold, fontSize = 13.sp) }
                )
                Tab(
                    selected = selectedSubTab == 1,
                    onClick = { selectedSubTab = 1 },
                    text = { Text("Service & Maintenance (${state.activeServiceRecords.size})", fontWeight = FontWeight.Bold, fontSize = 13.sp) }
                )
            }

            if (selectedSubTab == 0) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 96.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (state.activeFuelLogs.isEmpty()) {
                        item {
                            Text(
                                text = "No fuel logs yet. Tap '+ Add Refill' to start tracking your mileage!",
                                modifier = Modifier.padding(24.dp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 14.sp
                            )
                        }
                    } else {
                        items(state.activeFuelLogs) { log ->
                            FuelLogItemCard(log = log)
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 96.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.activeServiceRecords) { record ->
                        ServiceRecordCard(record = record)
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddFuelDialog(
            currentOdo = state.activeVehicle?.currentOdo ?: 0,
            onDismiss = { showAddDialog = false },
            onConfirm = { odo, liters, cost, station, fullTank ->
                onAddFuelLog(odo, liters, cost, station, fullTank)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun FuelLogItemCard(log: FuelLog) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = StatusGreenBg,
                modifier = Modifier.size(42.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.LocalGasStation, contentDescription = null, tint = StatusGreen, modifier = Modifier.size(22.dp))
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = log.stationName, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = "₹${log.cost.toInt()}", fontWeight = FontWeight.Black, fontSize = 15.sp, color = Navy700)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${log.date} • ${log.odometer} km",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${log.liters} Liters (₹${String.format("%.2f", log.cost / log.liters)}/L)",
                    fontSize = 11.sp,
                    color = StatusGreen,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun ServiceRecordCard(record: ServiceRecord) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = record.title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurface)
                Text(text = "₹${record.cost.toInt()}", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Amber600)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${record.date} • At ${record.odometer} km",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = record.notes,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun AddFuelDialog(
    currentOdo: Int,
    onDismiss: () -> Unit,
    onConfirm: (odometer: Int, liters: Double, cost: Double, station: String, isFullTank: Boolean) -> Unit
) {
    var odoText by remember { mutableStateOf((currentOdo + 350).toString()) }
    var litersText by remember { mutableStateOf("25.0") }
    var costText by remember { mutableStateOf("2500") }
    var stationText by remember { mutableStateOf("IOCL Petrol Bunk, Dindigul") }
    var isFullTank by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Log Fuel Refill (எரிபொருள் பதிவு)", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = odoText,
                    onValueChange = { odoText = it },
                    label = { Text("Current Odometer (km)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = litersText,
                    onValueChange = { litersText = it },
                    label = { Text("Fuel Liters") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = costText,
                    onValueChange = { costText = it },
                    label = { Text("Total Amount (₹)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = stationText,
                    onValueChange = { stationText = it },
                    label = { Text("Fuel Station / Location") },
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Full Tank Refill?", fontSize = 14.sp)
                    Switch(checked = isFullTank, onCheckedChange = { isFullTank = it })
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val odo = odoText.toIntOrNull() ?: currentOdo
                    val liters = litersText.toDoubleOrNull() ?: 10.0
                    val cost = costText.toDoubleOrNull() ?: 1000.0
                    onConfirm(odo, liters, cost, stationText, isFullTank)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Navy700)
            ) {
                Text("Save Log")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
