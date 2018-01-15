package com.bux.azenkovich.tradingbot.dto.order;

import lombok.Getter;
import lombok.Setter;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Getter
@Setter
public class OrderProductDto {
    private String securityId;
    private String symbol;
    private String displayName;
}
