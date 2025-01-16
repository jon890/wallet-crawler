package com.bifos.walletcrawler.core.dto

import java.math.BigDecimal
import java.math.BigInteger

data class GetAccountInfoResponse(
    val jsonrpc: String,
    val result: Result,
) {
    data class Result(
        val context: Context,
        val value: Value
    ) {
        data class Context(
            val apiVersion: String,
            val slot: Int
        )

        data class Value(
            val data: List<Any>,
            val executable: Boolean,
            val lamports: BigInteger,
            val owner: String,
            val rentEpoch: BigInteger,
            val space: String
        ) {

        }
    }
}