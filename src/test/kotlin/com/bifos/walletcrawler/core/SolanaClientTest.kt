package com.bifos.walletcrawler.core

import com.bifos.walletcrawler.core.client.SolanaClient
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SolanaClientTest(@Autowired val sut: SolanaClient) {

    @Test
    @DisplayName("솔라나 계정정보가 반환된다")
    fun getAccountInfoTest() {
        // given
        val accountId = "vines1vzrYbzLMRdu58ou5XTby4qAqVRLmqo36NKPTg"

        // when
        val accountInfo = sut.getAccountInfo(accountId)
            .block()

        // then
        assertNotNull(accountInfo)
    }
}