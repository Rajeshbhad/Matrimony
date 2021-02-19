package com.example.marathimatrimony

data class WriteRegistration(
        val profileCreatedFor: String? ="",
        val name: String = "",
        val dateOfBirth: String? ="",
        val gender: String? = "",
        val religion: String? = "",
        val motherTongue: String? = "",
        val countryCode: String? ="",
        val phoneNumber: String="",
        val Email: String = "",
        val password:String="",
)

