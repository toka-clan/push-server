package com.toka.push.core.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDate

@Embeddable
class MonthlyAlarm(
    @Column(name = "monthly_fixed_day")
    val fixedDay: Int
) {
    fun isMatchedDay(date: LocalDate): Boolean {
        return date.dayOfMonth == fixedDay
    }
}