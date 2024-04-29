package com.example.teamup.auth.data.source.local


import androidx.datastore.core.Serializer
import com.example.teamup.auth.data.source.remote.model.User
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<User> {
    override val defaultValue: User
        get() = User()

    override suspend fun readFrom(input: InputStream): User {
        return try {
            Json.decodeFromString(
                deserializer = User.serializer(),
                string = input.readBytes().decodeToString()

            )
        } catch (e: SerializationException) {
            e.stackTrace
            defaultValue
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = User.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}