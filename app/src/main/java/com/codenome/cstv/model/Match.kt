package com.codenome.cstv.model

import android.os.Parcelable
import com.codenome.cstv.utils.parseDate
import kotlinx.parcelize.Parcelize
import java.time.format.TextStyle
import java.util.*

@Parcelize
data class Match(
    val id: Int?,
    val begin_at: String?,
    val name: String?,
    val serie: Serie?,
    val status: String?,
    val opponents: List<Opponent>?,
    val original_scheduled_at: String?,
    val serie_id: String?,
    val league_id: String?,
    val tournament_id: String?,
    val league: League?,
    val number_of_games: Int?,
    val end_at: String?,
    val rescheduled: String?,
    val scheduled_at: String?,
    val match_type: String?,
    val isRunning: Boolean? = false
) : Parcelable {
    val firstOpponent get() = opponents?.getOrNull(0)?.opponent
    val secondOpponent get() = opponents?.getOrNull(1)?.opponent

    val date
        get(): String {
            val str = begin_at?.let { it.parseDate() }
                ?: scheduled_at?.let { it.parseDate() }

            val today = System.currentTimeMillis()
            val c = Calendar.getInstance()
            c.timeInMillis = today
            val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)
            val strDay = str?.dayOfMonth?.equals(dayOfMonth)


            val strWeek = if (strDay == true) "Hoje,"
            else str?.dayOfWeek?.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                ?.replaceFirstChar { it.uppercase() }
                ?.replace(".", ",")
            return "$strWeek ${str?.toLocalTime()}"
        }

    val leagueSerieName
        get():String? {
            return if (serie?.name.isNullOrBlank()) {
                league?.name
            } else {
                "${serie?.name} - ${league?.name}"
            }
        }
}
