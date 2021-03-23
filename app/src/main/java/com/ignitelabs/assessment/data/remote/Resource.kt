package com.ignitelabs.assessment.response.remote

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.ignitelabs.assessment.data.remote.Status

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class  Resource<T>(
    @param:NonNull @field:NonNull
    val status: Status, @param:Nullable @field:Nullable
    val response: T, @param:Nullable @field:Nullable @get:Nullable
    val error: String
) {
    companion object {

        fun <T> success(@Nullable response: T): Resource<T>? {
            return Resource(Status.SUCCESS, response, "")
        }

        fun <T> error(@Nullable error: String, @Nullable response: T): Resource<T> {
            return Resource(Status.ERROR, response, error)
        }

        fun <T> loading(@Nullable response: T): Resource<T>? {
            return Resource(Status.LOADING, response, "")
        }
    }
}