package com.example.teamup.core.model

data class ValidationResult(
    val successful:Boolean,
    val errorMessage:String? = null
)