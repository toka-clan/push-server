package com.toka.push.core.infrastructure.jpa

import com.toka.push.core.domain.ReAlarmHistory
import org.springframework.data.jpa.repository.JpaRepository

interface ReAlarmHistoryRepository: JpaRepository<ReAlarmHistory, Long> {

}