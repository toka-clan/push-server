package kr.toka.push.core.application

import kr.toka.push.core.domain.AlarmHistory
import kr.toka.push.core.domain.DailyAlarm
import kr.toka.push.core.domain.WeeklyAlarm
import kr.toka.push.core.domain.enums.RepeatType
import kr.toka.push.core.infrastructure.jpa.AlarmHistoryRepository
import kr.toka.push.web.PushAlarmRegisterRequest
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Service
class PushAlarmCommandService(
    private val alarmHistoryRepository: AlarmHistoryRepository,
) {
    fun createAlarmHistory(request: PushAlarmRegisterRequest): Long {
        validateRequest(request)
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
                    request.weeklyRepeatPeriods.joinToString(",")
                ),
                monthlyAlarm = null,
            )
        }
        val savedAlarm = alarmHistoryRepository.save(alarmHistory)
        return savedAlarm.messageId!!
    }

    private fun validateRequest(request: PushAlarmRegisterRequest) {
        if (request.repeatType == RepeatType.DAILY) {
            if (request.weeklyRepeatPeriods.isNotEmpty()) {
                throw IllegalArgumentException("잘못된 요청입니다. weeklyRepeatPeriods 값이 포함되면 안됨")
            }
        }
    }
}