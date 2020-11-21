package io.ambershogun.mentatus.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "User")
class User(
        @Id
        var chatId: Long,
        var locale: String
) {
    var lastActive: LocalDateTime? = null
}