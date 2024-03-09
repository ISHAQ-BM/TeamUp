package com.example.teamup.auth.domain.use_case

import com.example.teamup.core.model.ValidationResult

class ValidatePasswordUseCase {
    operator fun invoke(password:String):ValidationResult{
        if (password.length < 8){
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters"
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}