package com.lennydennis.mobilechallenge.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exercise(
    val exerciseId: String,
    val name: String,
    val practicedAtBpm: Int
) : Parcelable