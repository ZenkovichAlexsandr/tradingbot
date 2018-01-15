package com.bux.azenkovich.tradingbot.websockets;

import com.bux.azenkovich.tradingbot.bot.TradingBot;
import com.bux.azenkovich.tradingbot.dto.websocket.WebSocketMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Slf4j
@Component
@WebSocket
@RequiredArgsConstructor
public class WebSocketHandler {
    private final TradingBot bot;
    private final ObjectMapper objectMapper;
    private final CountDownLatch closeLatch = new CountDownLatch(1);

    @SuppressWarnings("unused")
    private Session session;

    /**
     * Await closing web socket chanel.
     */
    public void awaitClose() throws InterruptedException {
        this.closeLatch.await();
    }

    /**
     * On close web socket chanel.
     */
    @OnWebSocketClose
    public void onClose(final int statusCode, final String reason) {
        this.session = null;
        this.closeLatch.countDown();
        log.info("Disconnected from the web socket chanel. " + statusCode + " " + reason);
    }

    /**
     * On connect to the web socket chanel.
     */
    @OnWebSocketConnect
    public void onConnect(final Session session) {
        this.session = session;
        log.info("Connected to the web socket chanel");
    }

    /**
     * On receive message.
     */
    @OnWebSocketMessage
    public void onMessage(final String message) {
        try {
            bot.trade(objectMapper.readValue(message, WebSocketMessageDto.class), session);
        } catch (final IOException ex) {
            log.error("Can not deserialize web socket message", ex);
        }
    }
}
