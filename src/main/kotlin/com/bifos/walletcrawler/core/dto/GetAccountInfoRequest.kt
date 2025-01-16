package com.bifos.walletcrawler.core.dto

data class GetAccountInfoRequest(
    val jsonrpc: String = "2.0",
    val id: Int = 1,
    val method: String = "getAccountInfo",
    val params: List<Any>
) {

    data class Params(
        val accountId: String,
        val encoding: Encoding
    ) {
        data class Encoding(
            val encoding: String = "base58"
        )

        fun toParams(): List<Any> {
            return listOf(accountId, encoding)
        }
    }
}