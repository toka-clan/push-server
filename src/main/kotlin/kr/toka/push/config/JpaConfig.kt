package kr.toka.push.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaAuditing
@EntityScan(basePackages = ["kr.toka.push.core"])
@EnableJpaRepositories(basePackages = ["kr.toka.push.core.infrastructure.jpa"])
@Configuration
class JpaConfig {
}