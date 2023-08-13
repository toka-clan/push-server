package kr.toka.push.core.domain

import kr.toka.push.core.domain.common.BaseEntity
import kr.toka.push.core.domain.enums.RepeatType
import kr.toka.push.core.domain.enums.ServiceType
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Table(name = "alarm_history")
@Entity
class AlarmHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    val messageId: Long?,

    @Column(name = "device_id")
    val deviceId: String,

    @Column(name = "push_token")
    val pushToken: String,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "service_type")
    val serviceType: ServiceType,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "repeat_type")
    val repeatType: RepeatType, // 매일(D), 요일(W), 달(M)

    @Column(name = "start_date")
    val startDate: LocalDate,

    @Column(name = "end_date")
    val endDate: LocalDate? = null,

    @Embedded
    val dailyAlarm: DailyAlarm? = null,

    @Embedded
    val weeklyAlarm: WeeklyAlarm? = null,

    @Embedded
    val monthlyAlarm: MonthlyAlarm? = null,
): BaseEntity() {
    fun isAlarmTarget(dateTime: LocalDateTime): Boolean {
        val isConditionMatched = when (repeatType) {
            RepeatType.DAILY -> isRepeatPeriodDate(dateTime.toLocalDate())
            RepeatType.WEEKLY -> weeklyAlarm!!.containsWeeklyPeriod(dateTime.toLocalDate().dayOfWeek)
            RepeatType.MONTHLY -> monthlyAlarm!!.isMatchedDay(dateTime.toLocalDate())
        }

        return isConditionMatched && dailyAlarm!!.isAlarmTargetTime(dateTime.toLocalTime())
    }

    private fun isRepeatPeriodDate(targetDate: LocalDate): Boolean {
        val period = ChronoUnit.DAYS.between(startDate, targetDate)
        return period >= 0L && (period % dailyAlarm!!.repeatPeriod) == 0L
    }
}