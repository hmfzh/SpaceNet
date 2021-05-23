package com.d3if0028.spacenet.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlanetModel (
    var bulan: String ?="",
    var desc: String ?="",
    var diameter: String ?="",
    var judul: String ?="",
    var poster: String ?="",
    var radius: String ?="",
    var tahun: String ?="",
): Parcelable