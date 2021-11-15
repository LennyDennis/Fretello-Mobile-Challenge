package com.lennydennis.mobilechallenge.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Session(
    val exercises: List<Exercise>,
    val name: String,
    val practicedOnDate: String
) : Parcelable