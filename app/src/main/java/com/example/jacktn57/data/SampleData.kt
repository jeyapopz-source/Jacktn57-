package com.example.jacktn57.data

import com.example.jacktn57.model.*

object SampleData {

    val initialVehicles = listOf(
        Vehicle(
            id = "v1",
            regNumber = "TN 57 BH 4529",
            modelName = "Tata Nexon XZ+ (Diesel)",
            vehicleType = VehicleType.CAR,
            fuelType = FuelType.DIESEL,
            insuranceExpiryDays = 42,
            pucExpiryDays = 14,
            fcExpiryDays = 320,
            currentOdo = 38450,
            serviceDueKm = 1550,
            policyNumber = "NEW-IND-88492048"
        ),
        Vehicle(
            id = "v2",
            regNumber = "TN 57 AU 1104",
            modelName = "Royal Enfield Classic 350",
            vehicleType = VehicleType.BIKE,
            fuelType = FuelType.PETROL,
            insuranceExpiryDays = 180,
            pucExpiryDays = 85,
            fcExpiryDays = 450,
            currentOdo = 14200,
            serviceDueKm = 800,
            policyNumber = "BAJAJ-ALL-993810"
        )
    )

    val initialFuelLogs = listOf(
        FuelLog("f1", "v1", "12 Sep 2026", 38450, 32.5, 3050.0, "HP Petrol Pump, Thadikombu Rd", true),
        FuelLog("f2", "v1", "28 Aug 2026", 37920, 30.0, 2820.0, "Indian Oil, Palani Bypass", true),
        FuelLog("f3", "v1", "10 Aug 2026", 37380, 31.0, 2910.0, "Bharat Petroleum, Batlagundu", true)
    )

    val initialServices = listOf(
        ServiceRecord("s1", "v1", "15 Jul 2026", "Scheduled 35,000 km Service", 5400.0, 35000, "Synthetic oil change, air & oil filter replacement, brake pad cleaning"),
        ServiceRecord("s2", "v1", "20 Mar 2026", "Wheel Alignment & Balancing", 1100.0, 30200, "4-wheel balancing & computerized laser alignment")
    )

    val rtoOffices = listOf(
        RtoOffice(
            id = "rto1",
            code = "TN-57",
            name = "Dindigul Regional Transport Office",
            tamilName = "திண்டுக்கல் முதன்மை வட்டாரப் போக்குவரத்து அலுவலகம்",
            address = "Collectorate Complex, Chettinayakkanpatti (Post), Thadikombu Road, Dindigul - 624001",
            phone = "04512460223",
            email = "rtotn57@nic.in",
            timings = "Mon - Fri: 10:00 AM - 5:45 PM",
            isMain = true
        ),
        RtoOffice(
            id = "rto2",
            code = "TN-57Z",
            name = "Palani Unit Office",
            tamilName = "பழனி மோட்டார் வாகன ஆய்வாளர் அலுவலகம்",
            address = "Dharapuram Main Road, Near ITI, Palani - 624601",
            phone = "04545242200",
            email = "unitpalani.tn57@nic.in",
            timings = "Mon - Fri: 10:00 AM - 5:45 PM"
        ),
        RtoOffice(
            id = "rto3",
            code = "TN-57Y",
            name = "Oddanchatram Unit Office",
            tamilName = "ஒட்டன்சத்திரம் வாகன ஆய்வாளர் அலுவலகம்",
            address = "Dharapuram Road, Near Market Committee, Oddanchatram - 624619",
            phone = "04553240100",
            email = "unitodd.tn57@nic.in",
            timings = "Mon - Fri: 10:00 AM - 5:00 PM"
        ),
        RtoOffice(
            id = "rto4",
            code = "TN-57X",
            name = "Batlagundu Unit Office",
            tamilName = "வத்தலகுண்டு வாகன ஆய்வு மையம்",
            address = "Periyakulam Road, Batlagundu - 624202",
            phone = "04543262300",
            email = "unitbatla.tn57@nic.in",
            timings = "Mon - Fri: 10:00 AM - 5:00 PM"
        )
    )

    val emergencyContacts = listOf(
        EmergencyContact("Highway Patrol & Towing (NHAI)", "National Highway Helpline (24x7)", "1033", "Highway"),
        EmergencyContact("Ambulance Service", "TN Emergency Medical Response", "108", "Medical"),
        EmergencyContact("Police Emergency", "Tamil Nadu Police Control Room", "100", "Police"),
        EmergencyContact("Dindigul Traffic Control", "City Traffic & Accident Assistance", "04512460100", "Local Traffic"),
        EmergencyContact("Fire & Rescue Service", "Tamil Nadu Fire & Rescue Department", "101", "Rescue"),
        EmergencyContact("Women Helpline", "24x7 Safety Assistance", "1091", "Safety")
    )

    val jackTireGuideSteps = listOf(
        RoadsideGuideStep(
            stepNumber = 1,
            title = "Secure the Vehicle & Hazard Lights",
            tamilTitle = "வாகனத்தை பாதுகாப்பாக நிறுத்துங்கள்",
            instruction = "Turn on Hazard Warning Lights. Park on a flat, solid surface away from speeding traffic. Engage handbrake firmly. For manual cars, shift into 1st gear or Reverse; for automatics, shift to Park (P).",
            safetyWarning = "Never attempt to change a tire on a slope or soft mud. Place the red reflective hazard triangle 50 meters behind your vehicle."
        ),
        RoadsideGuideStep(
            stepNumber = 2,
            title = "Prepare Spare Wheel & Tools",
            tamilTitle = "உதிரி சக்கரம் மற்றும் கருவிகள்",
            instruction = "Retrieve the spare tire, mechanical/scissor jack, and wheel lug wrench from your trunk. Place the spare wheel flat under the car frame near the flat tire as an emergency safety cushion."
        ),
        RoadsideGuideStep(
            stepNumber = 3,
            title = "Loosen Lug Nuts Before Lifting",
            tamilTitle = "நட்டுகளை லேசாக தளர்த்தவும்",
            instruction = "Using the lug wrench, turn each nut counter-clockwise (anticlockwise) by 1/2 to 1 turn while the tire is still touching the ground. Do NOT remove them yet.",
            safetyWarning = "If the nut is stubborn, use your body weight by stepping on the wrench arm while keeping your hands firmly on the car."
        ),
        RoadsideGuideStep(
            stepNumber = 4,
            title = "Position the Jack at Frame Notch",
            tamilTitle = "ஜாக் பொருத்தும் புள்ளி",
            instruction = "Locate the vehicle's reinforced jacking notch under the metal sill (usually 6-8 inches behind the front wheel or ahead of the rear wheel). Position the jack directly underneath.",
            safetyWarning = "Do NOT place the jack under the floor pan or suspension arm. Lifting incorrect spots can crush sheet metal or flip the car."
        ),
        RoadsideGuideStep(
            stepNumber = 5,
            title = "Raise Car & Swap the Tire",
            tamilTitle = "காரை உயர்த்தி சக்கரத்தை மாற்றவும்",
            instruction = "Rotate the jack handle clockwise to raise the car until the flat tire clears the road by 2-3 inches. Unscrew the lug nuts completely, remove the flat tire, and mount the inflated spare tire.",
            safetyWarning = "NEVER place any part of your body (arms, legs, head) under a vehicle supported only by a jack."
        ),
        RoadsideGuideStep(
            stepNumber = 6,
            title = "Hand-Tighten & Lower the Jack",
            tamilTitle = "நட்டுகளை பொருத்தி ஜாக்கை இறக்கவும்",
            instruction = "Screw on the lug nuts by hand in a cross / star pattern until snug. Turn the jack handle counter-clockwise to lower the car until tires rest on ground. Remove jack and fully torque all nuts diagonally."
        )
    )

    val jumpStartSteps = listOf(
        RoadsideGuideStep(
            stepNumber = 1,
            title = "Position Donor Vehicle",
            tamilTitle = "உதவி வாகனத்தை நிறுத்தவும்",
            instruction = "Park donor vehicle close to your car so jumper cables reach, without the cars touching. Turn off both engines, turn off headlights, AC, and audio systems."
        ),
        RoadsideGuideStep(
            stepNumber = 2,
            title = "Connect RED (+) Positive Cable",
            tamilTitle = "சிவப்பு (+) பாசிட்டிவ் கேபிள்",
            instruction = "Attach one RED clamp to the positive (+) terminal of the dead battery. Attach the other RED clamp to the positive (+) terminal of the good donor battery.",
            safetyWarning = "Ensure red and black clamps NEVER touch each other during connection."
        ),
        RoadsideGuideStep(
            stepNumber = 3,
            title = "Connect BLACK (-) Negative Cable",
            tamilTitle = "கருப்பு (-) நெகட்டிவ் கேபிள்",
            instruction = "Attach one BLACK clamp to the negative (-) terminal of the good battery. Attach the final BLACK clamp to an unpainted metal bolt on the dead car's engine block (ground).",
            safetyWarning = "Do NOT connect the negative clamp directly to the dead battery terminal to avoid spark ignition."
        ),
        RoadsideGuideStep(
            stepNumber = 4,
            title = "Start Donor Car, Then Dead Car",
            tamilTitle = "காரை ஸ்டார்ட் செய்து சார்ஜ் செய்யவும்",
            instruction = "Start donor car engine and idle for 3-5 minutes. Then start your car. Once started, remove cables in EXACT REVERSE ORDER: Black from ground, Black from donor, Red from donor, Red from dead car."
        )
    )

    val trafficFines = listOf(
        TrafficFine("Riding Two-Wheeler Without Helmet", "Sec 129 / 194D", "₹1,000", "High"),
        TrafficFine("Driving Without Seatbelt", "Sec 138(3) / 194B", "₹1,000", "Medium"),
        TrafficFine("Dangerous / Reckless Over-Speeding", "Sec 112 / 183", "₹1,000 - ₹2,000", "High"),
        TrafficFine("Jumping Red Traffic Signal", "Sec 184", "₹1,000 - ₹5,000", "High"),
        TrafficFine("Using Mobile Phone While Driving", "Sec 184(c)", "₹1,000 - ₹5,000", "Critical"),
        TrafficFine("Driving Without Valid Insurance", "Sec 146 / 196", "₹2,000 (3 mo prison)", "High"),
        TrafficFine("Driving Without Driving License (DL)", "Sec 3 / 181", "₹5,000", "Critical"),
        TrafficFine("Drunken Driving (Blood Alcohol > 30mg)", "Sec 185", "₹10,000 (6 mo prison)", "Fatal")
    )

    val trafficSignQuizzes = listOf(
        TrafficSignQuiz(
            id = 1,
            question = "What does an inverted red triangle sign indicate on Tamil Nadu roads?",
            signSymbol = "▽",
            options = listOf("Stop", "Give Way / Yield", "No Entry", "One Way"),
            correctIndex = 1,
            explanation = "An inverted triangle is the mandatory 'Give Way' sign instructing drivers to yield right-of-way to main road traffic."
        ),
        TrafficSignQuiz(
            id = 2,
            question = "What is the legal speed limit for motorcycles on Tamil Nadu state highways unless otherwise posted?",
            signSymbol = "⚡",
            options = listOf("80 km/h", "60 km/h", "50 km/h", "40 km/h"),
            correctIndex = 1,
            explanation = "Under TN Motor Vehicles Rules, two-wheeler speed limit on state highways is capped at 60 km/h."
        ),
        TrafficSignQuiz(
            id = 3,
            question = "A circular blue sign with a white directional arrow indicates:",
            signSymbol = "➔",
            options = listOf("Caution Ahead", "Mandatory Direction", "Parking Available", "Speed Limit"),
            correctIndex = 1,
            explanation = "Blue circles signify mandatory instructions that must be strictly obeyed."
        ),
        TrafficSignQuiz(
            id = 4,
            question = "What is the mandatory distance to place the reflective emergency triangle behind a stalled car on highway?",
            signSymbol = "⚠️",
            options = listOf("5 meters", "15 meters", "50 meters", "200 meters"),
            correctIndex = 2,
            explanation = "Placing the triangle at least 50 meters back gives oncoming high-speed vehicles adequate reaction time."
        ),
        TrafficSignQuiz(
            id = 5,
            question = "What is the validity period of a newly issued Learner's License (LLR) in Tamil Nadu?",
            signSymbol = "L",
            options = listOf("30 Days", "6 Months", "1 Year", "2 Years"),
            correctIndex = 1,
            explanation = "An LLR is valid for 6 months across India, and one can apply for the permanent driving license after 30 days."
        )
    )

    val vehicleKitChecklist = listOf(
        "Functional Mechanical or Hydraulic Jack",
        "Wheel Lug Wrench (Cross Spanner)",
        "Inflated Spare Wheel (Check PSI monthly)",
        "Foldable Red Reflective Warning Triangle",
        "First Aid Kit (Bandages, Antiseptic, Gauze)",
        "Jumper Cables (Minimum 10-gauge)",
        "Portable 12V Tire Air Inflator / Pressure Gauge",
        "Heavy Duty Flashlight / Torch",
        "Set of Spare Fuses (10A, 15A, 20A)",
        "Vehicle Documents (RC, Insurance copy, PUC card)"
    )
}
