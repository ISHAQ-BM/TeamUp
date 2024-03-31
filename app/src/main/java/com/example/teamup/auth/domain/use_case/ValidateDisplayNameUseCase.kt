package com.example.teamup.auth.domain.use_case

import com.example.teamup.core.model.ValidationResult

class ValidateDisplayNameUseCase {
    operator fun invoke(displayName:String): ValidationResult {
        if (displayName.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "The display name can't be blank"
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}