package com.example.teamup.auth.domain.use_case

import com.example.teamup.core.model.ValidationResult

class ValidateFullNameUseCase {
    operator fun invoke(fullName:String): ValidationResult {
        if (fullName.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if (fullName.isEmpty()){
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be empty"
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}