package io.ambershogun.mentatus.core.notification.price.threshold.service

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import org.springframework.stereotype.Service
import yahoofinance.Stock
import yahoofinance.YahooFinance
import java.util.concurrent.TimeUnit

@Service
class StockService {

    private val stockCache = CacheBuilder
            .newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(15, TimeUnit.SECONDS)
            .build(object : CacheLoader<String, Stock?>() {
                override fun load(ticker: String): Stock? {
                    val stock = YahooFinance.get("$ticker.ME")
                    if (stock != null) {
                        return stock
                    }

                    return YahooFinance.get(ticker)
                }
            })

    fun getStocks(tickers: Array<String>?): MutableMap<String, Stock> {
        if (tickers == null) {
            return mutableMapOf()
        }
        return stockCache.getAll(tickers.toList()).mapValues { it.value!! }.toMutableMap()
    }

    fun getStock(ticker: String): Stock? {
        return stockCache.get(ticker)
    }
}
