package com.bifos.walletcrawler.core.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class GetAccountInfoRequest(
    @JsonIgnore
    val accountId: String,

    @JsonIgnore
    val encoding: String = "base58",
) : BaseSolanaRequest<List<Any>>(
    method = "getAccountInfo",
    params = Params(accountId, Params.Encoding(encoding)).toParams()
) {

    data class Params(
        val accountId: String,
        val encoding: Encoding
    ) {
        data class Encoding(
            val encoding: String
        )

        fun toParams(): List<Any> {
            return listOf(accountId, encoding)
        }
    }
}