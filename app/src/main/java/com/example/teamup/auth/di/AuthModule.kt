package com.example.teamup.auth.di

import android.content.Context
import com.example.teamup.BuildConfig
import com.example.teamup.auth.core.SIGN_IN_REQUEST
import com.example.teamup.auth.core.SIGN_UP_REQUEST
import com.example.teamup.auth.data.source.remote.AuthRemoteDataSource
import com.example.teamup.auth.data.source.remote.AuthNetwork
import com.example.teamup.auth.data.repository.AuthRepositoryImpl
import com.example.teamup.auth.data.source.remote.AuthRemoteDataSourceImpl
import com.example.teamup.auth.domain.repository.AuthRepository
import com.example.teamup.auth.domain.use_case.AuthUseCase
import com.example.teamup.auth.domain.use_case.ConfirmEmailUseCase
import com.example.teamup.auth.domain.use_case.ExchangeResetCodeUseCase
import com.example.teamup.auth.domain.use_case.ForgotPasswordUseCase
import com.example.teamup.auth.domain.use_case.ResetPasswordUseCase
import com.example.teamup.auth.domain.use_case.SendVerificationEmailUseCase
import com.example.teamup.auth.domain.use_case.LoginWithEmailAndPasswordUseCase
import com.example.teamup.auth.domain.use_case.SignUpWithEmailAndPasswordUseCase
import com.example.teamup.auth.domain.use_case.InitiateGoogleOneTapFlow
import com.example.teamup.auth.domain.use_case.SignUserWithGoogleUseCse
import com.example.teamup.auth.domain.use_case.ValidateConfirmationPasswordUseCase
import com.example.teamup.auth.domain.use_case.ValidateEmailUseCase
import com.example.teamup.auth.domain.use_case.ValidateFullNameUseCase
import com.example.teamup.auth.domain.use_case.ValidatePasswordUseCase
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthApi(): AuthNetwork {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AuthNetwork::class.java)
    }




    @Singleton
    @Provides
    @Named(SIGN_IN_REQUEST)
    fun provideSignInRequest() = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                .setFilterByAuthorizedAccounts(true)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    @Singleton
    @Provides
    @Named(SIGN_UP_REQUEST)
    fun provideSignUpRequest() = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    @Singleton
    @Provides
    fun provideOneTapClient(
        @ApplicationContext
        context: Context
    ) = Identity.getSignInClient(context)

    @Singleton
    @Provides
    fun provideLogInWithEmailAndPasswordUseCase(authRepository: AuthRepository) =
        LoginWithEmailAndPasswordUseCase(authRepository)


    @Singleton
    @Provides
    fun provideSignUpUseCase(authRepository: AuthRepository) = SignUpWithEmailAndPasswordUseCase(authRepository)

    @Singleton
    @Provides
    fun provideValidateEmailUseCase() = ValidateEmailUseCase()

    @Singleton
    @Provides
    fun provideValidatePasswordUseCase() = ValidatePasswordUseCase()

    @Singleton
    @Provides
    fun provideForgotPasswordUseCase(authRepository: AuthRepository) =
        ForgotPasswordUseCase(authRepository)

    @Singleton
    @Provides
    fun provideConfirmEmailUseCase(authRepository: AuthRepository) =
        ConfirmEmailUseCase(authRepository)

    @Singleton
    @Provides
    fun provideSendVerificationEmailUseCase(authRepository: AuthRepository) =
        SendVerificationEmailUseCase(authRepository)

    @Singleton
    @Provides
    fun provideResetPasswordUseCase(authRepository: AuthRepository) =
        ResetPasswordUseCase(authRepository)

    @Singleton
    @Provides
    fun provideSignUserWithGoogleUseCase(authRepository: AuthRepository) =
        SignUserWithGoogleUseCse(authRepository)

    @Singleton
    @Provides
    fun provideExchangeResetCodeUseCase(authRepository: AuthRepository) =
        ExchangeResetCodeUseCase(authRepository)

    @Singleton
    @Provides
    fun provideValidateConfirmationPasswordUseCase() =
        ValidateConfirmationPasswordUseCase()





    @Singleton
    @Provides
    fun provideValidateFullNameUseCase() =
        ValidateFullNameUseCase()

    @Singleton
    @Provides
    fun provideAuthUseCase(
        loginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase,
        signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase,
        validatePasswordUseCase: ValidatePasswordUseCase,
        validateEmailUseCase: ValidateEmailUseCase,
        forgotPasswordUseCase: ForgotPasswordUseCase,
        confirmEmailUseCase: ConfirmEmailUseCase,
        initiateGoogleOneTapFlowUseCase: InitiateGoogleOneTapFlow,
        resetPasswordUseCase: ResetPasswordUseCase,
        validateFullNameUseCase: ValidateFullNameUseCase,
        signUserWithGoogleUseCse: SignUserWithGoogleUseCse,
        sendVerificationEmailUseCase: SendVerificationEmailUseCase,
        exchangeResetCodeUseCase: ExchangeResetCodeUseCase,
        validateConfirmationPasswordUseCase: ValidateConfirmationPasswordUseCase

    ) = AuthUseCase(
        loginWithEmailAndPasswordUseCase,
        signUpWithEmailAndPasswordUseCase,
        validateEmailUseCase,
        validatePasswordUseCase,
        validateFullNameUseCase,
        forgotPasswordUseCase,
        confirmEmailUseCase,
        initiateGoogleOneTapFlowUseCase,
        resetPasswordUseCase,
        signUserWithGoogleUseCse,
        sendVerificationEmailUseCase,
        exchangeResetCodeUseCase,
        validateConfirmationPasswordUseCase
    )


}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindAuthDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource
}

