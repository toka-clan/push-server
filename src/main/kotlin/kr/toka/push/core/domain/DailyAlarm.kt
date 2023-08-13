package kr.toka.push.core.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.Duration
import java.time.LocalTime

@Embeddable
class DailyAlarm(
    @Column(name = "daily_repeat_period")
    val repeatPeriod: Int,    // 알림 반복 주기 (default: 1)

    @Column(name = "daily_start_time")
    val startTime: LocalTime,  // HHmmss
) {

    fun isAlarmTargetTime(targetTime: LocalTime): Boolean {
        val between = Duration.between(startTime, targetTime)
        return between.seconds in 0..59
    }

    // 저장시LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"))
}