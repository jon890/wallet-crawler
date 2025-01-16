package com.bifos.walletcrawler.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.netty.channel.ChannelOption
import io.netty.handler.logging.LogLevel
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat
import java.time.Duration


@Configuration
class WebClientConfig(
    private val objectMapper: ObjectMapper
) {

    private val log = LoggerFactory.getLogger(WebClientConfig::class.java)

    @Bean
    fun httpClient(): HttpClient {
        val httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofSeconds(5))
            .wiretap(
                "reactor.netty.http.client.HttpClient",
                LogLevel.DEBUG,
                AdvancedByteBufFormat.TEXTUAL
            )

        return httpClient
    }

    @Bean
    fun exchangeStrategies(): ExchangeStrategies {
        return ExchangeStrategies.builder()
            .codecs {
                it.defaultCodecs().jackson2JsonEncoder(
                    Jackson2JsonEncoder(
                        objectMapper,
                        MediaType.APPLICATION_JSON
                    )
                )
                it.defaultCodecs().jackson2JsonDecoder(
                    Jackson2JsonDecoder(
                        objectMapper,
                        MediaType.APPLICATION_JSON
                    )
                )
                it.defaultCodecs().maxInMemorySize(-1) // unlimited
            }
            .build()
    }

    @Bean
    fun webClient(httpClient: HttpClient, exchangeStrategies: ExchangeStrategies): WebClient {
        val webClient = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .exchangeStrategies(exchangeStrategies)
            .build()

        return webClient
    }
}