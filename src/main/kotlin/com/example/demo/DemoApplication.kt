package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.socket.config.WebSocketMessageBrokerStats
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport

@SpringBootApplication
@EnableScheduling
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@Configuration
class WebSocketConfiguration : WebSocketMessageBrokerConfigurationSupport() {

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/stomp")
    }
}

@Component
class WebSocketMetrics(private val webSocketMessageBrokerStats: WebSocketMessageBrokerStats) {

    @Scheduled(initialDelay = 500, fixedDelay = 1000)
    fun printMetrics() {
        println(webSocketMessageBrokerStats.stompSubProtocolStatsInfo)
    }
}