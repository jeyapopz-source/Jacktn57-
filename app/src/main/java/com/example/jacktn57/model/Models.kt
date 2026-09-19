package com.example.jacktn57.model

enum class VehicleType(val label: String) {
    CAR("Car / Four Wheeler"),
    BIKE("Bike / Scooter"),
    AUTO("Auto Rickshaw"),
    TRUCK("Commercial / LCV")
}

enum class FuelType(val label: String) {
    PETROL("Petrol"),
    DIESEL("Diesel"),
    ELECTRIC("Electric (EV)"),
    CNG("CNG")
}

data class Vehicle(
    val id: String,
    val regNumber: String,
    val modelName: String,
    val vehicleType: VehicleType,
    val fuelType: FuelType,
    val insuranceExpiryDays: Int,
    val pucExpiryDays: Int,
    val fcExpiryDays: Int,
    val currentOdo: Int,
    val serviceDueKm: Int,
    val policyNumber: String
)

data class FuelLog(
    val id: String,
    val vehicleId: String,
    val date: String,
    val odometer: Int,
    val liters: Double,
    val cost: Double,
    val stationName: String,
    val isFullTank: Boolean = true
)

data class ServiceRecord(
    val id: String,
    val vehicleId: String,
    val date: String,
    val title: String,
    val cost: Double,
    val odometer: Int,
    val notes: String
)

data class RtoOffice(
    val id: String,
    val code: String,
    val name: String,
    val tamilName: String,
    val address: String,
    val phone: String,
    val email: String,
    val timings: String,
    val isMain: Boolean = false
)

data class EmergencyContact(
    val title: String,
    val subtitle: String,
    val phone: String,
    val category: String
)

data class RoadsideGuideStep(
    val stepNumber: Int,
    val title: String,
    val tamilTitle: String,
    val instruction: String,
    val safetyWarning: String? = null
)

data class TrafficSignQuiz(
    val id: Int,
    val question: String,
    val signSymbol: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class TrafficFine(
    val violation: String,
    val section: String,
    val penalty: String,
    val severity: String
)
