package com.example.jacktn57.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.example.jacktn57.model.FuelType
import com.example.jacktn57.model.Vehicle
import com.example.jacktn57.model.VehicleType
import com.example.jacktn57.ui.JackUiState
import com.example.jacktn57.ui.theme.*

@Composable
fun GarageScreen(
    state: JackUiState,
    onSelectVehicle: (String) -> Unit,
    onAddVehicle: (reg: String, model: String, type: VehicleType, fuel: FuelType, insDays: Int, pucDays: Int, odo: Int, policy: String) -> Unit
) {
    var showAddVehicleDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddVehicleDialog = true },
                containerColor = Amber500,
                contentColor = Navy900
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Vehicle")
                    Text("Add Vehicle", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "MY GARAGE & DOCUMENT LOCKER",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "வாகன ஆவணப் பெட்டகம்",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Navy700
                    ) {
                        Text(
                            text = "${state.vehicles.size} Vehicles",
                            color = Amber500,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            items(state.vehicles) { vehicle ->
                val isSelected = vehicle.id == state.selectedVehicleId
                GarageVehicleCard(
                    vehicle = vehicle,
                    isSelected = isSelected,
                    onSelect = { onSelectVehicle(vehicle.id) }
                )
            }
        }
    }

    if (showAddVehicleDialog) {
        AddVehicleDialog(
            onDismiss = { showAddVehicleDialog = false },
            onConfirm = { reg, model, type, fuel, ins, puc, odo, policy ->
                onAddVehicle(reg, model, type, fuel, ins, puc, odo, policy)
                showAddVehicleDialog = false
            }
        )
    }
}

@Composable
fun GarageVehicleCard(
    vehicle: Vehicle,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 4.dp else 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) Amber500 else Color(0xFFE2E8F0),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onSelect() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = if (isSelected) Amber500 else Navy700,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = if (vehicle.vehicleType == VehicleType.BIKE)
                                    Icons.Default.TwoWheeler else Icons.Default.DirectionsCar,
                                contentDescription = null,
                                tint = if (isSelected) Navy900 else Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }

                    Column {
                        Text(
                            text = vehicle.regNumber,
                            fontWeight = FontWeight.Black,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = vehicle.modelName,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                if (isSelected) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Amber100
                    ) {
                        Text(
                            text = "ACTIVE",
                            color = Amber600,
                            fontWeight = FontWeight.Black,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = Color(0xFFE2E8F0))
            Spacer(modifier = Modifier.height(12.dp))

            // Document status grid
            Text(
                text = "DOCUMENT EXPIRY TIMELINE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))

            DocumentExpiryRow(
                title = "Insurance Policy",
                daysLeft = vehicle.insuranceExpiryDays,
                docNumber = vehicle.policyNumber
            )
            Spacer(modifier = Modifier.height(6.dp))
            DocumentExpiryRow(
                title = "PUC (Pollution Certificate)",
                daysLeft = vehicle.pucExpiryDays,
                docNumber = "Valid TN Transport"
            )
            Spacer(modifier = Modifier.height(6.dp))
            DocumentExpiryRow(
                title = "Fitness Certificate (FC)",
                daysLeft = vehicle.fcExpiryDays,
                docNumber = "RTO Verified"
            )
        }
    }
}

@Composable
fun DocumentExpiryRow(
    title: String,
    daysLeft: Int,
    docNumber: String
) {
    val isDanger = daysLeft <= 0
    val isWarning = daysLeft in 1..30
    val statusColor = when {
        isDanger -> StatusRed
        isWarning -> StatusOrange
        else -> StatusGreen
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
            Text(text = docNumber, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = statusColor.copy(alpha = 0.12f)
        ) {
            Text(
                text = if (isDanger) "Expired" else "$daysLeft days remaining",
                color = statusColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun AddVehicleDialog(
    onDismiss: () -> Unit,
    onConfirm: (reg: String, model: String, type: VehicleType, fuel: FuelType, insDays: Int, pucDays: Int, odo: Int, policy: String) -> Unit
) {
    var regNumber by remember { mutableStateOf("TN 57 ") }
    var modelName by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(VehicleType.CAR) }
    var selectedFuel by remember { mutableStateOf(FuelType.PETROL) }
    var insDaysText by remember { mutableStateOf("180") }
    var pucDaysText by remember { mutableStateOf("90") }
    var odoText by remember { mutableStateOf("15000") }
    var policyNumber by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Vehicle to Locker", fontWeight = FontWeight.Bold) },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = regNumber,
                    onValueChange = { regNumber = it.uppercase() },
                    label = { Text("Registration Number (e.g. TN 57 AB 1234)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = modelName,
                    onValueChange = { modelName = it },
                    label = { Text("Make & Model (e.g. Swift, Pulsar 150)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = odoText,
                    onValueChange = { odoText = it },
                    label = { Text("Current Odometer (km)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = insDaysText,
                    onValueChange = { insDaysText = it },
                    label = { Text("Insurance Days Remaining") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = pucDaysText,
                    onValueChange = { pucDaysText = it },
                    label = { Text("PUC (Pollution) Days Remaining") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = policyNumber,
                    onValueChange = { policyNumber = it },
                    label = { Text("Insurance Policy Number (Optional)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (regNumber.isNotBlank() && modelName.isNotBlank()) {
                        onConfirm(
                            regNumber,
                            modelName,
                            selectedType,
                            selectedFuel,
                            insDaysText.toIntOrNull() ?: 180,
                            pucDaysText.toIntOrNull() ?: 90,
                            odoText.toIntOrNull() ?: 10000,
                            policyNumber.ifBlank { "POL-TN57-${System.currentTimeMillis() % 10000}" }
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Navy700)
            ) {
                Text("Save Vehicle")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
