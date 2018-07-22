package shuffler.youtubeshuffler.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import shuffler.youtubeshuffler.sockets.MyMessageHandler


@Configuration
@EnableWebSocket
class WebsocketConfig : WebSocketConfigurer {

    @Bean
    fun myMessageHandler(): WebSocketHandler {
        return MyMessageHandler()
    }

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(myMessageHandler(), "/my-websocket-endpoint")
    }

}