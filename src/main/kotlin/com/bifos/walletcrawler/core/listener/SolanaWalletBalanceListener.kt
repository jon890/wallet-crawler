package com.bifos.walletcrawler.core.listener

import com.bifos.walletcrawler.core.client.SolanaClient
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import java.time.Duration

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class SolanaWalletBalanceListener(
    private val solanaClient: SolanaClient
) {

    var onRunning = false
    val nextTick = Duration.ofSeconds(30)

    @PostConstruct
    fun start() {
        onRunning = true
    }

    fun getBalance() {

    }

    fun close() {
        onRunning = false
    }
}