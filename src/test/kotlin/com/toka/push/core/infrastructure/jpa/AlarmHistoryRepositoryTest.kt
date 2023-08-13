package com.toka.push.core.infrastructure.jpa

import com.toka.push.core.domain.AlarmHistory
import com.toka.push.core.domain.DailyAlarm
import com.toka.push.core.domain.WeeklyAlarm
import com.toka.push.core.domain.common.findByIdOrThrow
import com.toka.push.core.domain.enums.RepeatType
import com.toka.push.core.domain.enums.ServiceType
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.TestPropertySource
import java.time.LocalDate
import java.time.LocalTime

@DataJpaTest
@TestPropertySource(properties = ["spring.config.location=classpath:application-test.yml"])
class AlarmHistoryRepositoryTest(
    private val alarmHistoryRepository: AlarmHistoryRepository,
    private val entityManager: TestEntityManager,
) : ExpectSpec({
    extensions(SpringTestExtension(SpringTestLifecycleMode.Root))

    context("daily 알람 이력 저장") {
        val entity = AlarmHistory(
            messageId = null,
            deviceId = "deviceId",
            pushToken = "token",
            serviceType = ServiceType.MEDIATE,
            repeatType = RepeatType.DAILY,
            startDate = LocalDate.of(2023, 7, 30),
            endDate = null,
            dailyAlarm = DailyAlarm(2, LocalTime.of(10, 30, 0)),
        )

        val savedEntity = alarmHistoryRepository.save(entity)
        entityManager.flush()
        entityManager.clear()

        expect("저장된 값 확인") {
            val actual = alarmHistoryRepository.findByIdOrThrow(savedEntity.messageId!!)
            actual.messageId.shouldNotBeNull()
            actual.dailyAlarm!!.repeatPeriod shouldBe 2
            actual.dailyAlarm!!.startTime shouldBe LocalTime.of(10, 30)
            actual.deviceId shouldBe "deviceId"
            actual.pushToken shouldBe "token"
            actual.serviceType shouldBe ServiceType.MEDIATE
            actual.repeatType shouldBe RepeatType.DAILY
            actual.startDate shouldBe LocalDate.of(2023, 7, 30)
        }
    }

    context("weekly 알람 이력 저장") {
        val entity = AlarmHistory(
            messageId = null,
            deviceId = "deviceId",
            pushToken = "token",
            serviceType = ServiceType.ONCEADAY,
            repeatType = RepeatType.WEEKLY,
            startDate = LocalDate.of(2023, 7, 30),
            endDate = null,
            dailyAlarm = DailyAlarm(repeatPeriod = 1, startTime = LocalTime.of(10, 30, 0)),
            weeklyAlarm = WeeklyAlarm("1,3"),
        )

        val savedEntity = alarmHistoryRepository.save(entity)
        entityManager.flush()
        entityManager.clear()

        expect("저장된 값 확인") {
            val actual = alarmHistoryRepository.findByIdOrThrow(savedEntity.messageId!!)
            actual.messageId.shouldNotBeNull()
            actual.dailyAlarm!!.repeatPeriod shouldBe 1
            actual.dailyAlarm!!.startTime shouldBe LocalTime.of(10, 30)
            actual.deviceId shouldBe "deviceId"
            actual.pushToken shouldBe "token"
            actual.serviceType shouldBe ServiceType.ONCEADAY
            actual.repeatType shouldBe RepeatType.WEEKLY
            actual.startDate shouldBe LocalDate.of(2023, 7, 30)
            actual.weeklyAlarm!!.repeatPeriods shouldBe "1,3"
        }
    }
})
