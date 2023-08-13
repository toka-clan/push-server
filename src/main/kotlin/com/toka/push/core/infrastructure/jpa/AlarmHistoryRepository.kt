package com.toka.push.core.infrastructure.jpa

import com.toka.push.core.domain.AlarmHistory
import org.springframework.data.jpa.repository.JpaRepository

interface AlarmHistoryRepository: JpaRepository<AlarmHistory, Long> {

}