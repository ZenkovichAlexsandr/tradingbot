package com.bux.azenkovich.tradingbot.dto.websocket;

import com.bux.azenkovich.tradingbot.dto.Dto;
import com.bux.azenkovich.tradingbot.enums.WebSocketOperation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author alexsandr
 * @since 12.01.18.
 */
@Getter
@Setter
public class WebSocketMessageDto implements Dto {
    @JsonProperty("t")
    private WebSocketOperation operation;
    private WebSocketBodyDto body;
}
