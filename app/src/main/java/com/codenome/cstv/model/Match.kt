package com.codenome.cstv.model

import android.os.Parcelable
import com.codenome.cstv.utils.*
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
            val startDate = begin_at?.parseDate() ?: scheduled_at?.parseDate()

            val today = System.currentTimeMillis()
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = today
            val strDay = startDate?.toCalendar()?.getDaysBetween(calendar)

            val strWeek = when {
                strDay == 0L && startDate.dayOfMonth == calendar.getActualDay() -> "Hoje,"
                ((startDate?.dayOfMonth ?: 0) - calendar.getActualDay()) == 1 -> "Amanhã,"
                (strDay ?: 0L) >= 3L -> startDate?.toCalendar()?.getStringDayAndMonth()
                else -> startDate?.dayOfWeek?.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                    ?.replaceFirstChar { it.uppercase() }
                    ?.replace(".", ",")
            }
            return "$strWeek ${startDate?.toLocalTime()}"
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
