package com.d3if0028.spacenet.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SumberAdapter (
        var nama: String ?="",
        var url: String ?=""
): Parcelable