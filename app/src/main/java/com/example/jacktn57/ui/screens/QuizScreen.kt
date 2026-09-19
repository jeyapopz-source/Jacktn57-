package com.example.jacktn57.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jacktn57.data.SampleData
import com.example.jacktn57.ui.QuizState
import com.example.jacktn57.ui.theme.*

@Composable
fun QuizScreen(
    quizState: QuizState,
    onAnswer: (Int) -> Unit,
    onNext: () -> Unit,
    onRestart: () -> Unit
) {
    val total = SampleData.trafficSignQuizzes.size

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Navy800),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Amber500,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.SportsScore, contentDescription = null, tint = Navy900)
                        }
                    }
                    Column {
                        Text(
                            text = "TN RTO LLR PRACTICE TEST",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Amber500
                        )
                        Text(
                            text = "வட்டாரப் போக்குவரத்து ஓட்டுநர் உரிம வினாடி வினா",
                            fontSize = 11.sp,
                            color = Color(0xFFCBD5E1)
                        )
                    }
                }
            }
        }

        if (quizState.isComplete) {
            item {
                QuizResultCard(
                    score = quizState.score,
                    total = total,
                    onRestart = onRestart
                )
            }
        } else {
            val currentQuestion = SampleData.trafficSignQuizzes[quizState.currentIndex]

            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Question ${quizState.currentIndex + 1} of $total",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "Score: ${quizState.score}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Amber600
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Road sign visual symbol badge
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .background(Color(0xFFEEF2F6), RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = currentQuestion.signSymbol,
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Navy700
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = currentQuestion.question,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Options
                        currentQuestion.options.forEachIndexed { index, option ->
                            val isSelected = quizState.selectedOption == index
                            val isCorrect = index == currentQuestion.correctIndex

                            val borderColor = when {
                                !quizState.isAnswered -> if (isSelected) Amber500 else Color(0xFFCBD5E1)
                                isCorrect -> StatusGreen
                                isSelected -> StatusRed
                                else -> Color(0xFFCBD5E1)
                            }

                            val containerColor = when {
                                !quizState.isAnswered -> MaterialTheme.colorScheme.surface
                                isCorrect -> StatusGreenBg
                                isSelected -> StatusRedBg
                                else -> MaterialTheme.colorScheme.surface
                            }

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = containerColor,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .border(width = 1.5.dp, color = borderColor, shape = RoundedCornerShape(10.dp))
                                    .clickable(enabled = !quizState.isAnswered) { onAnswer(index) }
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Text(
                                        text = "${'A' + index}.",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = if (quizState.isAnswered && isCorrect) StatusGreen else MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = option,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.weight(1f)
                                    )
                                    if (quizState.isAnswered) {
                                        if (isCorrect) {
                                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = StatusGreen)
                                        } else if (isSelected) {
                                            Icon(Icons.Default.Cancel, contentDescription = null, tint = StatusRed)
                                        }
                                    }
                                }
                            }
                        }

                        // Explanation after answering
                        if (quizState.isAnswered) {
                            Spacer(modifier = Modifier.height(14.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFF1F5F9)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = "Official Rule & Explanation:",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = Navy700
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = currentQuestion.explanation,
                                        fontSize = 12.sp,
                                        color = TextPrimary,
                                        lineHeight = 16.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = onNext,
                                colors = ButtonDefaults.buttonColors(containerColor = Navy700),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(
                                    text = if (quizState.currentIndex + 1 < total) "Next Question" else "View Results",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizResultCard(
    score: Int,
    total: Int,
    onRestart: () -> Unit
) {
    val passed = score >= (total * 0.6)

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = CircleShape,
                color = if (passed) StatusGreenBg else StatusRedBg,
                modifier = Modifier.size(72.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = if (passed) Icons.Default.Verified else Icons.Default.Replay,
                        contentDescription = null,
                        tint = if (passed) StatusGreen else StatusRed,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (passed) "RTO LLR Test Passed!" else "Review Rules & Retry",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (passed)
                    "Great job! You scored $score out of $total. You are well prepared for the Tamil Nadu RTO computer exam."
                else
                    "You scored $score out of $total. Passing score is 3 or more. Review road signs and retry.",
                textAlign = TextAlign.Center,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onRestart,
                colors = ButtonDefaults.buttonColors(containerColor = Navy700),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Retake Quiz", fontWeight = FontWeight.Bold)
            }
        }
    }
}
