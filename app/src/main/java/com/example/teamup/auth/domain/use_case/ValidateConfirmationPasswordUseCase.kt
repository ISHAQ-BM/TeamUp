package com.example.teamup.auth.domain.use_case

import com.example.teamup.core.model.ValidationResult

class ValidateConfirmationPasswordUseCase {

    operator fun invoke(password:String, confirmationPassword:String): ValidationResult {
        if (password != confirmationPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "Confirmation password is incorrect"
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}