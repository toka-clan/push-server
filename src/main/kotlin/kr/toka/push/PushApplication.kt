package kr.toka.push

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PushApplication

fun main(args: Array<String>) {
    runApplication<PushApplication>(*args)
}
