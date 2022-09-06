package com.codenome.cstv.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val id: Int? = 0,
    val age: Int? = 0,
    val birth_year: Int? = 2000,
    val birthday: String? = "",
    val first_name: String? = "Not Found",
    val hometown: String? = "",
    val image_url: String? = "",
    val last_name: String? = "",
    val name: String? = "No Name",
    val nationality: String? = "Brazil",
    val role: String? = "",
    val slug: String? = "",
    val team: Int = 0
) : Parcelable {
    val fullName: String?
        get() {
            if (first_name.isNullOrBlank() && last_name.isNullOrBlank()) {
                return null
            }

            var playerName = "$first_name $last_name"
            if (playerName.length > 10) {
                playerName = "${playerName.take(10)}..."
            }
            return playerName
        }
}