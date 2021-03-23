package io.ambershogun.mentatus.core.message

import org.springframework.stereotype.Service

@Service
class MessageHandlerRegistry {

    private var registry: HashMap<String, MessageHandler> = HashMap()

    fun register(messageHandler: MessageHandler) {
        registry[messageHandler.messageRegEx()] = messageHandler
    }

    fun getHandler(inputMessage: String): MessageHandler {
        return registry.entries.stream()
                .filter { e -> inputMessage.toLowerCase().matches(Regex(e.value.messageRegEx())) }
                .findFirst()
                .map { e -> e.value }
                .orElseThrow { UnsupportedOperationException(inputMessage) }
    }
}