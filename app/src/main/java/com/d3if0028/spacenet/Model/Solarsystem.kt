package com.d3if0028.spacenet.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Solarsystem (
    var desc: String ?="",
    var judul: String ?="",
    var poster: String ?="",
    var sumber: String ?="",
    var content: String ?="",
    var title: String ?="",
): Parcelable