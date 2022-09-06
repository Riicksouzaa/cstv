package com.codenome.cstv.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val id: Int?,
    val name: String?,
    val acronym: String?,
    val image_url: String?,
    val slug: String?,
    val players: List<Player>
) : Parcelable