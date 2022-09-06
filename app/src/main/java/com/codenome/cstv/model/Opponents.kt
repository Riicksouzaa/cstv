package com.codenome.cstv.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Opponent(
    val opponent: OpponentData?,
    val type: String?
) : Parcelable {
    @Parcelize
    data class OpponentData(
        val acronym: String?,
        val id: Int?,
        val image_url: String?,
        val location: String?,
        val modified_at: String,
        val name: String?,
        val slug: String?
    ) : Parcelable
}