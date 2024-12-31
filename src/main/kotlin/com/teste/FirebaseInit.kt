package com.teste

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream

fun initializeFirebase() {
    val serviceAccount = FileInputStream("src/main/resources/serviceAccountKey.json")
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://blog-abc21-default-rtdb.europe-west1.firebasedatabase.app")
        .build()
    FirebaseApp.initializeApp(options)
}