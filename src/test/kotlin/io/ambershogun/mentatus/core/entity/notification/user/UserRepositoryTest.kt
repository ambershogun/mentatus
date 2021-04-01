package io.ambershogun.mentatus.core.entity.notification.user

import io.ambershogun.mentatus.AbstractTest
import io.ambershogun.mentatus.core.entity.user.Settings
import io.ambershogun.mentatus.core.entity.user.User
import org.junit.Test
import kotlin.test.assertEquals

class UserRepositoryTest : AbstractTest() {

    @Test
    fun `test find user to notify by schedule`() {
        userRepository.saveAll(listOf(
                User(1).apply {
                    settings = Settings().apply {
                        isScheduledNotificationsEnabled = true
                    }
                },
                User(2).apply {
                    settings = Settings().apply {
                        isScheduledNotificationsEnabled = false
                    }
                },
                User(3).apply {
                    settings = Settings().apply {
                        isScheduledNotificationsEnabled = true
                    }
                }
        ))

        val usersToNotify = userRepository.findUserToNotifyBySchedule()

        assertEquals(2, usersToNotify.size)
        assertEquals(1, usersToNotify[0].chatId)
        assertEquals(3, usersToNotify[1].chatId)
    }
}