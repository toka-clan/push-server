package kr.toka.push.send

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import mu.KotlinLogging
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class Sender(
    private val firebaseMessaging: FirebaseMessaging
): ApplicationRunner {
    private val logger = KotlinLogging.logger {}

    override fun run(args: ApplicationArguments?) {
        val notification = Notification.builder()
            .setTitle("hello")
            .setBody("first message")
            .build()

        val message = Message.builder()
            .setToken(TOKEN)
            .setNotification(notification)
            .build()

//        firebaseMessaging.send(message)
    }

    companion object {
        const val TOKEN = "d3OtKB8oTtyBHAs6wkEIyI:APA91bHRbSwzEIQJaXDaM4_oTwlIPgemDOX6KFKAZx5OdLTX6wakLk0uj33XclLbB2NvxfRpum2V3K4CdPiPdYhIQr0xK1sPy619WECyzJOPUdFcEa4mSp-VmgBHUdz-S6qhdAdn7Y3T"
    }
}