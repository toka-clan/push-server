package com.toka.push.web

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/push/alarm")
class PushAlarmController(
    private val pushAlarmCommandService: com.toka.push.core.application.PushAlarmCommandService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: PushAlarmRegisterRequest): ResponseEntity<PushAlarmRegisterResponse> {
        val alarmHistoryId = pushAlarmCommandService.createAlarmHistory(request)
        return ResponseEntity.ok(PushAlarmRegisterResponse(alarmHistoryId.toString()))
    }
}

