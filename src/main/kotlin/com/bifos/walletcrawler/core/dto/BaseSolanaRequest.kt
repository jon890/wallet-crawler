package com.bifos.walletcrawler.core.dto

open class BaseSolanaRequest<T>(
    val jsonrpc: String = "2.0",
    val id: Int = 1,
    val method: String,
    val params: T
) {
}