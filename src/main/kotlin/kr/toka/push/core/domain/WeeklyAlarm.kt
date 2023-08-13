package kr.toka.push.core.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.DayOfWeek

@Embeddable
class WeeklyAlarm(
    @Column(name = "weekly_repeat_period")
    val repeatPeriods: String, // Monday(1), Thuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(7)
) {
    fun containsWeeklyPeriod(dayOfWeek: DayOfWeek): Boolean {
        return repeatPeriods.split(",")
            .map { DayOfWeek.of(it.toInt()) }.toSet()
            .contains(dayOfWeek)
    }
}