package kr.toka.push.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kr.toka.push.core.application.PushAlarmCommandService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "푸시", description = "푸시기능 관련 api입니다.")
@RestController
@RequestMapping("/api/v1/push/alarm")
class PushAlarmController(
    private val pushAlarmCommandService: PushAlarmCommandService,
) {

    @Operation(summary = "푸시 등록 메소드")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "성공",
                content = [
                    Content(schema = Schema(implementation = PushAlarmRegisterResponse::class))
                ]
            ),
            ApiResponse(
                responseCode = "400",
                description = "실패",
                content = [
                    Content(schema = Schema(implementation = PushAlarmRegisterResponse::class))
                ]
            )
        ]
    )
    @PostMapping("/register")
    fun register(@Valid @RequestBody request: PushAlarmRegisterRequest): ResponseEntity<PushAlarmRegisterResponse> {
        val alarmHistoryId = pushAlarmCommandService.createAlarmHistory(request)
        return ResponseEntity.ok(PushAlarmRegisterResponse(alarmHistoryId.toString()))
    }
}

