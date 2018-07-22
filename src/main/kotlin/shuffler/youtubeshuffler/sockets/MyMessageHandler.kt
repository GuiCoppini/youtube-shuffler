package shuffler.youtubeshuffler.sockets

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class MyMessageHandler : TextWebSocketHandler() {
    override fun afterConnectionClosed(session: WebSocketSession, status: org.springframework.web.socket.CloseStatus) {
        println("Connection closed!")
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        session.sendMessage(TextMessage("You are now connected to the server. This is the first message."))
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("Message received: " + message.payload)
    }
}