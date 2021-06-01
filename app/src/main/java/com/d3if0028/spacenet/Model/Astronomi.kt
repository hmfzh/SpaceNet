package com.d3if0028.spacenet.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Astronomi (
        var desc: String ?="",
        var judul: String ?="",
        var poster: String ?="",
        var date: String ?="",
        ): Parcelable