package com.example.jacktn57.ui

import androidx.lifecycle.ViewModel
import com.example.jacktn57.data.SampleData
import com.example.jacktn57.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class QuizState(
    val currentIndex: Int = 0,
    val selectedOption: Int? = null,
    val isAnswered: Boolean = false,
    val score: Int = 0,
    val isComplete: Boolean = false
)

data class JackUiState(
    val vehicles: List<Vehicle> = SampleData.initialVehicles,
    val selectedVehicleId: String = SampleData.initialVehicles.first().id,
    val fuelLogs: List<FuelLog> = SampleData.initialFuelLogs,
    val serviceRecords: List<ServiceRecord> = SampleData.initialServices,
    val checkedKitItems: Set<String> = setOf(
        SampleData.vehicleKitChecklist[0],
        SampleData.vehicleKitChecklist[1],
        SampleData.vehicleKitChecklist[2],
        SampleData.vehicleKitChecklist[3]
    ),
    val quizState: QuizState = QuizState(),
    val rtoSearchQuery: String = "",
    val fineSearchQuery: String = ""
) {
    val activeVehicle: Vehicle?
        get() = vehicles.find { it.id == selectedVehicleId } ?: vehicles.firstOrNull()

    val activeFuelLogs: List<FuelLog>
        get() = fuelLogs.filter { it.vehicleId == selectedVehicleId }

    val activeServiceRecords: List<ServiceRecord>
        get() = serviceRecords.filter { it.vehicleId == selectedVehicleId }

    val averageMileage: Double
        get() {
            val logs = activeFuelLogs.sortedBy { it.odometer }
            if (logs.size < 2) return 17.5 // default baseline km/l
            val totalDistance = logs.last().odometer - logs.first().odometer
            val totalFuel = logs.drop(1).sumOf { it.liters }
            return if (totalFuel > 0) String.format("%.1f", totalDistance / totalFuel).toDouble() else 17.5
        }

    val totalFuelSpent: Double
        get() = activeFuelLogs.sumOf { it.cost }
}

class JackViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(JackUiState())
    val uiState: StateFlow<JackUiState> = _uiState.asStateFlow()

    fun selectVehicle(vehicleId: String) {
        _uiState.update { it.copy(selectedVehicleId = vehicleId) }
    }

    fun addVehicle(
        regNumber: String,
        modelName: String,
        vehicleType: VehicleType,
        fuelType: FuelType,
        insuranceDays: Int,
        pucDays: Int,
        currentOdo: Int,
        policyNumber: String
    ) {
        val newVehicle = Vehicle(
            id = UUID.randomUUID().toString(),
            regNumber = regNumber.uppercase().trim(),
            modelName = modelName.trim(),
            vehicleType = vehicleType,
            fuelType = fuelType,
            insuranceExpiryDays = insuranceDays,
            pucExpiryDays = pucDays,
            fcExpiryDays = 365,
            currentOdo = currentOdo,
            serviceDueKm = 2500,
            policyNumber = policyNumber.trim()
        )
        _uiState.update { state ->
            val updatedList = state.vehicles + newVehicle
            state.copy(vehicles = updatedList, selectedVehicleId = newVehicle.id)
        }
    }

    fun addFuelLog(
        odometer: Int,
        liters: Double,
        cost: Double,
        stationName: String,
        isFullTank: Boolean
    ) {
        val activeId = _uiState.value.selectedVehicleId
        val newLog = FuelLog(
            id = UUID.randomUUID().toString(),
            vehicleId = activeId,
            date = "Today",
            odometer = odometer,
            liters = liters,
            cost = cost,
            stationName = stationName.ifBlank { "Fuel Station, Dindigul" },
            isFullTank = isFullTank
        )
        _uiState.update { state ->
            val updatedLogs = listOf(newLog) + state.fuelLogs
            // Also update vehicle current odometer if higher
            val updatedVehicles = state.vehicles.map { v ->
                if (v.id == activeId && odometer > v.currentOdo) {
                    v.copy(currentOdo = odometer)
                } else v
            }
            state.copy(fuelLogs = updatedLogs, vehicles = updatedVehicles)
        }
    }

    fun toggleKitItem(item: String) {
        _uiState.update { state ->
            val updated = state.checkedKitItems.toMutableSet()
            if (updated.contains(item)) {
                updated.remove(item)
            } else {
                updated.add(item)
            }
            state.copy(checkedKitItems = updated)
        }
    }

    fun answerQuiz(optionIndex: Int) {
        val currentQuiz = _uiState.value.quizState
        if (currentQuiz.isAnswered) return

        val question = SampleData.trafficSignQuizzes[currentQuiz.currentIndex]
        val isCorrect = optionIndex == question.correctIndex
        val newScore = if (isCorrect) currentQuiz.score + 1 else currentQuiz.score

        _uiState.update { state ->
            state.copy(
                quizState = state.quizState.copy(
                    selectedOption = optionIndex,
                    isAnswered = true,
                    score = newScore
                )
            )
        }
    }

    fun nextQuestion() {
        val currentQuiz = _uiState.value.quizState
        val total = SampleData.trafficSignQuizzes.size
        if (currentQuiz.currentIndex + 1 < total) {
            _uiState.update { state ->
                state.copy(
                    quizState = state.quizState.copy(
                        currentIndex = currentQuiz.currentIndex + 1,
                        selectedOption = null,
                        isAnswered = false
                    )
                )
            }
        } else {
            _uiState.update { state ->
                state.copy(
                    quizState = state.quizState.copy(isComplete = true)
                )
            }
        }
    }

    fun restartQuiz() {
        _uiState.update { state ->
            state.copy(quizState = QuizState())
        }
    }

    fun setRtoSearch(query: String) {
        _uiState.update { it.copy(rtoSearchQuery = query) }
    }

    fun setFineSearch(query: String) {
        _uiState.update { it.copy(fineSearchQuery = query) }
    }
}
