package com.toka.push.send

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
        // todo: 해당앱으로부터 json파일 넣기
        val resource = ClassPathResource("firebase/helloworld-f4ac6-firebase-adminsdk-d953o-5e629d4445.json")
        val refreshToken = resource.inputStream
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(refreshToken))
            .setConnectTimeout(3000)
            .setReadTimeout(4000)
            .build()

        return FirebaseMessaging.getInstance(FirebaseApp.initializeApp(options))
    }
}