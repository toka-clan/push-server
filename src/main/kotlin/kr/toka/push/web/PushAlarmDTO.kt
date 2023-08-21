package kr.toka.push.web

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Null
import kr.toka.push.core.domain.enums.RepeatType
import kr.toka.push.core.domain.enums.ServiceType

data class PushAlarmRegisterRequest(
    @NotNull
    @Schema(
        description = "기기를 식별할수 있는 ID",
        defaultValue = "defaultDeviceId"
    )
    val deviceId: String,               // 기기식별Id
    @NotNull
    @Schema(
        description = "FCM으로부터 발급 받은 기기별 토큰값 ex)d3OtKB8oTtyBHAs6wkEIyI:APA91bHRbSwzEIQJaXDaM4_oTwlIPgemDOX6KFKAZx5OdLTX6wakLk0uj33XclLbB2NvxfRpum2V3K4CdPiPdYhIQr0xK1sPy619WECyzJOPUdFcEa4mSp-VmgBHUdz-S6qhdAdn7Y3T",
        defaultValue = "d3OtKB8oTtyBHAs6wkEIyI:APA91bHRbSwzEIQJaXDaM4_oTwlIPgemDOX6KFKAZx5OdLTX6wakLk0uj33XclLbB2NvxfRpum2V3K4CdPiPdYhIQr0xK1sPy619WECyzJOPUdFcEa4mSp-VmgBHUdz-S6qhdAdn7Y3T"
    )
    val pushToken: String,              // 알림토큰
    @NotNull
    @Schema(
        description = "서비스 타입(MEDIATE, ONCEADAY)",
        defaultValue = "MEDIATE"
    )
    val serviceType: ServiceType,       // MEDIATE, ONCEADAY
    @NotNull
    @Schema(
        description = "알림 반복주기 타입(DAILY, WEEKLY, MONTHLY)",
        defaultValue = "DAILY"
    )
    val repeatType: RepeatType,         // DAILY, WEEKLY, MONTHLY
    @NotNull
    @Schema(
        description = "알림 시작일자",
        defaultValue = "20230801"
    )
    val startDate: String,              // 시작일자 (20230801)
    @Null
    @Schema(
        description = "알림 종료일자",
        defaultValue = "20230810"
    )
    val endDate: String?,               // 끝일자 (20230808)
    @NotNull
    @Schema(
        description = "매일알림 반복주기",
        defaultValue = "1"
    )
    val dailyRepeatPeriod: Int = 1,     // 매일알림반복주기 (2일마다)
    @NotNull
    @Schema(
        description = "알림 시작시간",
        defaultValue = "133000"
    )
    val dailyStartTime: String,         // 알림시작시간(133000)
    @Null
    @Schema(
        description = "반복주기요일 1(Monday), 2(Thuesday), 3(Wednesday), 4(Thursday), 5(Friday), 6(Saturday), 7(Sunday)",
        defaultValue = "[\"1\",\"2\"]"
    )
    val weeklyRepeatPeriods: List<String> = listOf()    // 반복주기요일 Monday(1), Thuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(7)
)

data class PushAlarmRegisterResponse(
    val pushMessageId: String,          // messageId
)