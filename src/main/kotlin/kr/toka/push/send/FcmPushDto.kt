package kr.toka.push.send

data class FcmPushDto(
    val targetUserId: Long,
    val title: String,
    val body: String,
)