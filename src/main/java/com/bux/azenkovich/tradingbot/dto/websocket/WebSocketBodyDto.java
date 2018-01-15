package com.bux.azenkovich.tradingbot.dto.websocket;

import com.bux.azenkovich.tradingbot.dto.CommonResponseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Getter
@Setter
public class WebSocketBodyDto extends CommonResponseDto {
    private String securityId;
    private String currentPrice;
}
