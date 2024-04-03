package com.sparta.sns

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    val id: String,
    val email: String,
    val password: String,
    val name: String
) : Parcelable
