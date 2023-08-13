package com.toka.push.core.domain.enums

enum class ServiceType(
    val workName: String
) {
    MEDIATE("약알림서비스"),
    ONCEADAY("OnceADay")
    ;

    companion object {
        operator fun invoke(type: String) = valueOf(type.uppercase())
    }
}