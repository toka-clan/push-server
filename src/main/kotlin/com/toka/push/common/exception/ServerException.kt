package com.toka.push.common.exception

sealed class ServerException(
    val code: Int,
    override val message: String,
) : RuntimeException(message)

data class NotFoundException(
    override val message: String,
) : com.toka.push.common.exception.ServerException(404, message)