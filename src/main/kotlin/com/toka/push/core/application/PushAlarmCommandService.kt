package com.toka.push.core.application

import com.toka.push.core.domain.AlarmHistory
import com.toka.push.core.domain.DailyAlarm
import com.toka.push.core.domain.WeeklyAlarm
import com.toka.push.core.infrastructure.jpa.AlarmHistoryRepository
import com.toka.push.web.PushAlarmRegisterRequest
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Service
class PushAlarmCommandService(
    private val alarmHistoryRepository: AlarmHistoryRepository,
) {
    fun createAlarmHistory(request: PushAlarmRegisterRequest): Long {
        val alarmHistory = with(request) {
            AlarmHistory(
                messageId = null,
                deviceId = deviceId,
                pushToken = pushToken,
                serviceType = serviceType,
                repeatType = repeatType,
                startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd")),
                endDate = endDate?.let { LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyyMMdd")) },
                dailyAlarm = DailyAlarm(
                    repeatPeriod = dailyRepeatPeriod,
                    startTime = LocalTime.parse(dailyStartTime, DateTimeFormatter.ofPattern("HHmmss"))
                ),
                weeklyAlarm = WeeklyAlarm(
                    request.weeklyRepeatPeriods?.joinToString(",") ?: ""
                ),
                monthlyAlarm = null,
            )
        }
        val savedAlarm = alarmHistoryRepository.save(alarmHistory)
        return savedAlarm.messageId!!
    }
}