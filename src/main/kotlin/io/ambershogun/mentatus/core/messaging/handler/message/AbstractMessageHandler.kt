package io.ambershogun.mentatus.core.messaging.handler.message

import io.ambershogun.mentatus.core.entity.user.User
import io.ambershogun.mentatus.core.entity.user.service.UserService
import io.ambershogun.mentatus.core.messaging.util.ResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import java.time.LocalDateTime
import java.util.*

abstract class AbstractMessageHandler : MessageHandler {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    protected lateinit var responseService: ResponseService

    final override fun handleMessage(chatId: Long, inputMessage: String): List<BotApiMethod<Message>> {
        val user = userService.findOrCreateUser(chatId)

        user.lastActive = LocalDateTime.now()
        userService.saveUser(user)

        return handleMessageInternal(user, inputMessage)
    }

    protected abstract fun handleMessageInternal(user: User, inputMessage: String): List<BotApiMethod<Message>>
}