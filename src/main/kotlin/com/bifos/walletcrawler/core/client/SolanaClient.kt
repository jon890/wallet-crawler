package com.bifos.walletcrawler.core.client

import com.bifos.walletcrawler.core.dto.GetAccountInfoRequest
import com.bifos.walletcrawler.core.dto.GetAccountInfoResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class SolanaClient(
    private val webClient: WebClient,
    private val objectMapper: ObjectMapper
) {

    private val log = LoggerFactory.getLogger(SolanaClient::class.java)

    private val client: WebClient = webClient.mutate()
        .baseUrl("https://api.testnet.solana.com")
        .build()

    fun getAccountInfo(accountId: String) : Mono<GetAccountInfoResponse> {
        val requestDto = GetAccountInfoRequest(accountId)
        val requestBodyValue = objectMapper.writeValueAsString(requestDto)

        log.debug("request body value : {}", requestBodyValue)

        return client.post()
            .bodyValue(requestBodyValue)
            .retrieve()
            .bodyToMono(GetAccountInfoResponse::class.java)
    }
}