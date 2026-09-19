package com.example.jacktn57.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jacktn57.R
import com.example.jacktn57.model.Vehicle
import com.example.jacktn57.ui.JackUiState
import com.example.jacktn57.ui.theme.*

@Composable
fun HomeScreen(
    state: JackUiState,
    onNavigateToTab: (Int) -> Unit,
    onSelectVehicle: (String) -> Unit
) {
    val context = LocalContext.current
    val vehicle = state.activeVehicle

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Hero Header with Highway Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_highway),
                    contentDescription = "Scenic Highway Near Dindigul",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Gradient scrim
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Navy900.copy(alpha = 0.85f)
                                )
                            )
                        )
                )
                // Overlay text
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            color = Amber500,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "TN-57",
                                color = Navy900,
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Text(
                            text = "திண்டுக்கல் வட்டார வழிகாட்டி",
                            color = Amber100,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text(
                        text = "Smart Vehicle & Roadside Hub",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Vehicle Switcher Row
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(
                    text = "MY VEHICLES",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.vehicles) { v ->
                        val isSelected = v.id == state.selectedVehicleId
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) Navy700 else MaterialTheme.colorScheme.surface,
                            tonalElevation = if (isSelected) 4.dp else 1.dp,
                            modifier = Modifier
                                .border(
                                    width = if (isSelected) 2.dp else 1.dp,
                                    color = if (isSelected) Amber500 else Color(0xFFE2E8F0),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable { onSelectVehicle(v.id) }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = if (v.vehicleType == com.example.jacktn57.model.VehicleType.BIKE)
                                        Icons.Default.TwoWheeler else Icons.Default.DirectionsCar,
                                    contentDescription = null,
                                    tint = if (isSelected) Amber500 else MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Column {
                                    Text(
                                        text = v.regNumber,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = v.modelName,
                                        fontSize = 11.sp,
                                        color = if (isSelected) Amber100 else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Active Vehicle Status Card
        if (vehicle != null) {
            item {
                VehicleStatusCard(
                    vehicle = vehicle,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    onManageClick = { onNavigateToTab(3) } // Tab 3 is Garage
                )
            }
        }

        // Quick Emergency Assistance Section
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(
                    text = "EMERGENCY HOTLINES (ஒரே தொடுதலில் அழைப்பு)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    EmergencyButton(
                        title = "1033",
                        subtitle = "NHAI Towing",
                        icon = Icons.Default.CarCrash,
                        color = Amber500,
                        textColor = Navy900,
                        modifier = Modifier.weight(1f)
                    ) {
                        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:1033"))
                        context.startActivity(dialIntent)
                    }

                    EmergencyButton(
                        title = "108",
                        subtitle = "Ambulance",
                        icon = Icons.Default.LocalHospital,
                        color = StatusRed,
                        textColor = Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:108"))
                        context.startActivity(dialIntent)
                    }

                    EmergencyButton(
                        title = "100",
                        subtitle = "Police SOS",
                        icon = Icons.Default.LocalPolice,
                        color = Navy700,
                        textColor = Color.White,
                        modifier = Modifier.weight(1f)
                    ) {
                        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:100"))
                        context.startActivity(dialIntent)
                    }
                }
            }
        }

        // Quick Action Hub Grid
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = "ASSISTANT TOOLS & SERVICES",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickActionCard(
                        title = "Jack Guide",
                        tamilTitle = "டயர் மாற்றும் வழிகாட்டி",
                        description = "Step-by-step tire swap & jacking points",
                        icon = Icons.Default.Build,
                        badge = "Emergency",
                        badgeColor = Amber500,
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateToTab(1) } // Tab 1 is Roadside Tools
                    )
                    QuickActionCard(
                        title = "TN-57 RTO",
                        tamilTitle = "திண்டுக்கல் RTO",
                        description = "Dindigul, Palani, contacts & fees",
                        icon = Icons.Default.AccountBalance,
                        badge = "TN-57",
                        badgeColor = ElectricBlue,
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateToTab(2) } // Tab 2 is RTO Directory
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickActionCard(
                        title = "Fuel & Mileage",
                        tamilTitle = "எரிபொருள் கணக்கு",
                        description = "Log refills & calculate mileage",
                        icon = Icons.Default.LocalGasStation,
                        badge = "Tracker",
                        badgeColor = StatusGreen,
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateToTab(4) } // Tab 4 is Fuel
                    )
                    QuickActionCard(
                        title = "LLR Quiz & Signs",
                        tamilTitle = "போக்குவரத்து விதிகள்",
                        description = "TN RTO test prep & traffic signs",
                        icon = Icons.Default.Quiz,
                        badge = "Practice",
                        badgeColor = Color(0xFF7B2CBF),
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateToTab(5) } // Tab 5 is Quiz
                    )
                }
            }
        }
    }
}

@Composable
fun VehicleStatusCard(
    vehicle: Vehicle,
    modifier: Modifier = Modifier,
    onManageClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // High-contrast Indian Number Plate representation
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color.White,
                    modifier = Modifier.border(1.5.dp, Color.Black, RoundedCornerShape(6.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFF0038A8),
                            modifier = Modifier.size(14.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text("IND", color = Color.White, fontSize = 6.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Text(
                            text = vehicle.regNumber,
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp,
                            color = Color.Black,
                            letterSpacing = 1.sp
                        )
                    }
                }

                TextButton(onClick = onManageClick) {
                    Text("View Locker", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = vehicle.modelName,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Odometer: ${vehicle.currentOdo} km • ${vehicle.fuelType.label}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = Color(0xFFE2E8F0))
            Spacer(modifier = Modifier.height(14.dp))

            // Document status pills
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatusPill(
                    title = "Insurance",
                    daysLeft = vehicle.insuranceExpiryDays,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                StatusPill(
                    title = "PUC (Pollution)",
                    daysLeft = vehicle.pucExpiryDays,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                StatusPill(
                    title = "Fitness (FC)",
                    daysLeft = vehicle.fcExpiryDays,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun StatusPill(
    title: String,
    daysLeft: Int,
    modifier: Modifier = Modifier
) {
    val isWarning = daysLeft in 1..30
    val isDanger = daysLeft <= 0
    val bgColor = when {
        isDanger -> StatusRedBg
        isWarning -> StatusOrangeBg
        else -> StatusGreenBg
    }
    val textColor = when {
        isDanger -> StatusRed
        isWarning -> StatusOrange
        else -> StatusGreen
    }

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = bgColor,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = if (isDanger) "Expired" else "$daysLeft days",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

@Composable
fun EmergencyButton(
    title: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = color,
        modifier = modifier
            .height(72.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(icon, contentDescription = null, tint = textColor, modifier = Modifier.size(18.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp,
                    color = textColor
                )
            }
            Text(
                text = subtitle,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = textColor.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
fun QuickActionCard(
    title: String,
    tamilTitle: String,
    description: String,
    icon: ImageVector,
    badge: String,
    badgeColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Surface(
                    shape = CircleShape,
                    color = badgeColor.copy(alpha = 0.15f),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icon, contentDescription = null, tint = badgeColor, modifier = Modifier.size(22.dp))
                    }
                }
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = badgeColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = badge,
                        color = badgeColor,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = tamilTitle,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 16.sp
            )
        }
    }
}
