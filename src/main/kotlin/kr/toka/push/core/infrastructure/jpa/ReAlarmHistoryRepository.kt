package kr.toka.push.core.infrastructure.jpa

import kr.toka.push.core.domain.ReAlarmHistory
import org.springframework.data.jpa.repository.JpaRepository

interface ReAlarmHistoryRepository: JpaRepository<ReAlarmHistory, Long> {

}