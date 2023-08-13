package com.toka.push.core.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.DayOfWeek

internal class WeeklyAlarmTest : FunSpec({

    context("요일 테스트") {
        val cut = WeeklyAlarm("1,3")

        cut.containsWeeklyPeriod(DayOfWeek.MONDAY) shouldBe true
        cut.containsWeeklyPeriod(DayOfWeek.TUESDAY) shouldBe false
        cut.containsWeeklyPeriod(DayOfWeek.WEDNESDAY) shouldBe true
        cut.containsWeeklyPeriod(DayOfWeek.THURSDAY) shouldBe false
        cut.containsWeeklyPeriod(DayOfWeek.FRIDAY) shouldBe false
        cut.containsWeeklyPeriod(DayOfWeek.SATURDAY) shouldBe false
        cut.containsWeeklyPeriod(DayOfWeek.SUNDAY) shouldBe false
    }
})
