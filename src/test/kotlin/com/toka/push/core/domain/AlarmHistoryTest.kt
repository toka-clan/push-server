package com.toka.push.core.domain

import com.toka.push.core.domain.enums.RepeatType
import com.toka.push.core.domain.enums.ServiceType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AlarmHistoryTest : FunSpec({
    context("daily 알림 대상 테스트") {
        val cut = AlarmHistory(
            messageId = null,
            deviceId = "deviceId",
            pushToken = "token",
            serviceType = ServiceType.MEDIATE,
            repeatType = RepeatType.DAILY,
            startDate = LocalDate.of(2023, 7, 30),
            endDate = null,
            dailyAlarm = DailyAlarm(2, LocalTime.of(10, 30, 0)),
        )

        cut.isAlarmTarget(LocalDateTime.of(2023, 7, 30, 10, 30, 0)) shouldBe true
        cut.isAlarmTarget(LocalDateTime.of(2023, 7, 30, 10, 30, 59)) shouldBe true
        cut.isAlarmTarget(LocalDateTime.of(2023, 7, 31, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 1, 10, 30, 59)) shouldBe true
    }

    context("weekly 알림 대상 테스트") {
        val cut = AlarmHistory(
            messageId = null,
            deviceId = "deviceId",
            pushToken = "token",
            serviceType = ServiceType.MEDIATE,
            repeatType = RepeatType.WEEKLY,
            startDate = LocalDate.of(2023, 7, 30),
            endDate = null,
            dailyAlarm = DailyAlarm(2, LocalTime.of(10, 30, 0)),
            weeklyAlarm = WeeklyAlarm("1,3"),
        )

        cut.isAlarmTarget(LocalDateTime.of(2023, 7, 30, 10, 30, 0)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 7, 30, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 7, 31, 10, 30, 59)) shouldBe true
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 2, 10, 30, 59)) shouldBe true
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 3, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 4, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 5, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 6, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 7, 10, 30, 59)) shouldBe true
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 8, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 9, 10, 30, 59)) shouldBe true
    }

    context("monthly 알림 대상 테스트") {
        val cut = AlarmHistory(
            messageId = null,
            deviceId = "deviceId",
            pushToken = "token",
            serviceType = ServiceType.MEDIATE,
            repeatType = RepeatType.MONTHLY,
            startDate = LocalDate.of(2023, 7, 30),
            endDate = null,
            dailyAlarm = DailyAlarm(2, LocalTime.of(10, 30, 0)),
            monthlyAlarm = MonthlyAlarm(3)
        )

        cut.isAlarmTarget(LocalDateTime.of(2023, 7, 30, 10, 30, 0)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 7, 30, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 7, 31, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 2, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 3, 10, 30, 59)) shouldBe true
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 4, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 5, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 6, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 7, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 8, 10, 30, 59)) shouldBe false
        cut.isAlarmTarget(LocalDateTime.of(2023, 8, 9, 10, 30, 59)) shouldBe false
    }
})