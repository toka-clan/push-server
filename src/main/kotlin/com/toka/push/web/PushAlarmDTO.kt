package com.toka.push.web

import com.toka.push.core.domain.enums.RepeatType
import com.toka.push.core.domain.enums.ServiceType

data class PushAlarmRegisterRequest(
    val deviceId: String,               // 기기식별Id
    val pushToken: String,              // 알림토큰
    val serviceType: ServiceType,       // MEDIATE, ONCEADAY
    val repeatType: RepeatType,         // DAILY, WEEKLY, MONTHLY
    val startDate: String,              // 시작일자 (20230801)
    val endDate: String?,               // 끝일자 (20230808)
    val dailyRepeatPeriod: Int = 1,     // 매일알림반복주기 (2일마다)
    val dailyStartTime: String,         // 알림시작시잔(133000)
    val weeklyRepeatPeriods: List<String>? = listOf()    // 반복주기요일 Monday(1), Thuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(7)
)

data class PushAlarmRegisterResponse(
    val messageId: String,          // messageId
)