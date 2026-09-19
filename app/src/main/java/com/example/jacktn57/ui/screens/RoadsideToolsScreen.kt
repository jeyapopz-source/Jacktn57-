package com.example.jacktn57.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jacktn57.data.SampleData
import com.example.jacktn57.model.RoadsideGuideStep
import com.example.jacktn57.ui.JackUiState
import com.example.jacktn57.ui.theme.*

@Composable
fun RoadsideToolsScreen(
    state: JackUiState,
    onToggleKitItem: (String) -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Jack & Tire", "Jump Start", "Tool Kit Checklist")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Tab Row
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Navy800,
            contentColor = Amber500
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp,
                            color = if (selectedTab == index) Amber500 else Color(0xFF94A3B8)
                        )
                    }
                )
            }
        }

        when (selectedTab) {
            0 -> TireChangeGuide()
            1 -> JumpStartGuide()
            2 -> KitChecklistTab(
                checkedItems = state.checkedKitItems,
                onToggleItem = onToggleKitItem
            )
        }
    }
}

@Composable
fun TireChangeGuide() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Amber100,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Amber600,
                        modifier = Modifier.size(28.dp)
                    )
                    Column {
                        Text(
                            text = "CRITICAL JACK SAFETY RULE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Navy900
                        )
                        Text(
                            text = "Never crawl or place any body part underneath a car supported only by a jack. Always place the spare wheel flat under the chassis as a safety blocker.",
                            fontSize = 12.sp,
                            color = Navy800,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        items(SampleData.jackTireGuideSteps) { step ->
            RoadsideStepCard(step = step)
        }
    }
}

@Composable
fun JumpStartGuide() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFE0F2FE),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ElectricBolt,
                        contentDescription = null,
                        tint = ElectricBlue,
                        modifier = Modifier.size(28.dp)
                    )
                    Column {
                        Text(
                            text = "12V BATTERY JUMP SEQUENCE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Navy900
                        )
                        Text(
                            text = "Red clamp = Positive (+). Black clamp = Negative (-) or Ground. Follow order strictly to prevent electrical short circuits.",
                            fontSize = 12.sp,
                            color = Navy800,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        items(SampleData.jumpStartSteps) { step ->
            RoadsideStepCard(step = step)
        }
    }
}

@Composable
fun KitChecklistTab(
    checkedItems: Set<String>,
    onToggleItem: (String) -> Unit
) {
    val total = SampleData.vehicleKitChecklist.size
    val completed = checkedItems.size
    val progress = if (total > 0) completed.toFloat() / total else 0f

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Emergency Readiness",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "அவசரக் கருவிகள் ஆயத்த நிலை",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = "$completed / $total",
                            fontWeight = FontWeight.Black,
                            fontSize = 20.sp,
                            color = if (completed == total) StatusGreen else Amber600
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = if (completed == total) StatusGreen else Amber500,
                        trackColor = Color(0xFFE2E8F0),
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (completed == total)
                            "✓ Your vehicle is fully equipped for highway travel!"
                        else
                            "Keep all 10 essential tools inside your boot before long trips.",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        items(SampleData.vehicleKitChecklist) { item ->
            val isChecked = checkedItems.contains(item)
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (isChecked) StatusGreen.copy(alpha = 0.4f) else Color(0xFFE2E8F0),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { onToggleItem(item) },
                        colors = CheckboxDefaults.colors(
                            checkedColor = StatusGreen,
                            uncheckedColor = Color(0xFF94A3B8)
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item,
                        fontSize = 14.sp,
                        fontWeight = if (isChecked) FontWeight.Medium else FontWeight.Normal,
                        color = if (isChecked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun RoadsideStepCard(step: RoadsideGuideStep) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = Navy700,
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "${step.stepNumber}",
                            color = Amber500,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = step.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = step.tamilTitle,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = step.instruction,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 18.sp
            )

            if (step.safetyWarning != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = StatusRedBg
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.WarningAmber,
                            contentDescription = null,
                            tint = StatusRed,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = step.safetyWarning,
                            fontSize = 11.sp,
                            color = StatusRed,
                            lineHeight = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}
