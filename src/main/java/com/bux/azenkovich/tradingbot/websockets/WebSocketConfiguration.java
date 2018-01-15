package com.bux.azenkovich.tradingbot.websockets;

import com.bux.azenkovich.tradingbot.properties.ApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Slf4j
@Configuration
public class WebSocketConfiguration {
    private final ApiProperties apiProperties;
    private final WebSocketHandler handler;

    WebSocketConfiguration(final ApiProperties apiProperties, final WebSocketHandler handler) {
        this.apiProperties = apiProperties;
        this.handler = handler;
        this.start();
    }

    /**
     * Start web socket client.
     */
    public void start() {
        final WebSocketClient client = new WebSocketClient();

        try {
            client.start();

            final URI echoUri = new URI(apiProperties.getWebSocketUrl());
            final ClientUpgradeRequest request = new ClientUpgradeRequest();
            request.setHeader(AUTHORIZATION, apiProperties.getToken());
            request.setHeader(ACCEPT_LANGUAGE, apiProperties.getAcceptLanguages());
            client.connect(handler, echoUri, request);
            handler.awaitClose();
        } catch (Exception ex) {
            log.error("Can not connect to the server", ex);
        }
    }
}

