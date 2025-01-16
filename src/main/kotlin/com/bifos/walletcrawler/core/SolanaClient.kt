package com.bifos.walletcrawler.core

import com.bifos.walletcrawler.core.dto.GetAccountInfoRequest
import com.bifos.walletcrawler.core.dto.GetAccountInfoResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class SolanaClient(
    private val webClient: WebClient,
    private val objectMapper: ObjectMapper
) {

    private val client: WebClient = webClient.mutate()
        .baseUrl("https://api.testnet.solana.com")
        .build()

    fun getAccountInfo(accountId: String) : Mono<GetAccountInfoResponse> {
        val requestDto = GetAccountInfoRequest(
            params = GetAccountInfoRequest.Params(
                accountId,
                encoding = GetAccountInfoRequest.Params.Encoding()
            ).toParams()
        )

        return client.post()
            .bodyValue(objectMapper.writeValueAsString(requestDto))
            .retrieve()
            .bodyToMono(GetAccountInfoResponse::class.java)
    }
}