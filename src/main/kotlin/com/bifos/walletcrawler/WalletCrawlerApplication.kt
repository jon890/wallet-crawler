package com.bifos.walletcrawler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WalletCrawlerApplication {
}

fun main(args: Array<String>) {
    runApplication<WalletCrawlerApplication>(*args)
}