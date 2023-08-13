package kr.toka.push.core.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import java.time.LocalTime

class DailyAlarmTest : FunSpec({

    context("시간 경계값 포함 테스트") {
        val cut = DailyAlarm(1, LocalTime.of(10, 30))
        withData(
            nameFn = {"10:30 비교 - ${it.time} ${it.expected}"},
            DailyTargetTime(LocalTime.of(10, 29, 59), false),
            DailyTargetTime(LocalTime.of(10, 30,0), true),
            DailyTargetTime(LocalTime.of(10, 30,10), true),
            DailyTargetTime(LocalTime.of(10, 30,20), true),
            DailyTargetTime(LocalTime.of(10, 30,30), true),
            DailyTargetTime(LocalTime.of(10, 30,40), true),
            DailyTargetTime(LocalTime.of(10, 30,50), true),
            DailyTargetTime(LocalTime.of(10, 30,59), true),
            DailyTargetTime(LocalTime.of(10, 31,0), false),
            DailyTargetTime(LocalTime.of(10, 31,1), false),
        ) { (time, expected) ->
            cut.isAlarmTargetTime(time) shouldBe expected
        }
    }
})

data class DailyTargetTime(val time: LocalTime, val expected: Boolean)