package com.example.marathimatrimony.datamodel

data class WriteProfessionalInformation(
    val highestEducation: String? ="",
    val collegeAndInstitution: String = "",
    val occupation: String? ="",
    val organization: String? = "",
    val employedIn: String? = "",
    val annualIncome: String? = "",
    val currencyType: String? = ""
)
