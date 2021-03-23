package io.ambershogun.mentatus.core.entity.notification.price

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "PriceNotification")
class PriceNotification(
        var chatId: Long,
        var ticker: String,
        var equitySign: EquitySign,
        var price: Double,
        var currency: String
) {

    @Id
    var id: String? = null

    override fun toString(): String {
        return "`${ticker} ${equitySign.sign} ${BigDecimal.valueOf(price).setScale(2)} $currency`"
    }
}