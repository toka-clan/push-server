package kr.toka.push.core.infrastructure.jpa

import kr.toka.push.core.domain.AlarmHistory
import org.springframework.data.jpa.repository.JpaRepository

interface AlarmHistoryRepository: JpaRepository<AlarmHistory, Long> {

}