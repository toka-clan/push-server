package com.toka.push.core.domain.enums

enum class RepeatType {
    DAILY,
    WEEKLY,
    MONTHLY
    ;

    companion object {
        operator fun invoke(type: String) = valueOf(type.uppercase())
    }
}