package com.toka.push.core.domain

import com.toka.push.core.domain.enums.ServiceType
import jakarta.persistence.*
import java.time.Duration
import java.time.LocalTime

// 결과 기록알림 (일회성)
@Table(name = "re_alarm_history")
@Entity
class ReAlarmHistory(
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

    @Column(name = "alarm_time")
    val alarmTime: LocalTime,  // HHmmss

    val procsYn: String,       // 처리여부
) {

    fun isAlarmTargetTime(targetTime: LocalTime): Boolean {
        val between = Duration.between(alarmTime, targetTime)
        return between.seconds in 0..59
    }
}