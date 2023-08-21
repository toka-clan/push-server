package kr.toka.push.send

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FcmConfig {

    @Bean
    fun firebaseMessaging(): FirebaseMessaging {
        val resource = ClassPathResource("firebase/toka-7f683-firebase-adminsdk-zbj59-5ae1edc4f7.json")
        val refreshToken = resource.inputStream
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(refreshToken))
            .setConnectTimeout(3000)
            .setReadTimeout(4000)
            .build()

        return FirebaseMessaging.getInstance(FirebaseApp.initializeApp(options))
    }
}