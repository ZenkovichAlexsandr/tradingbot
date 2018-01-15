package com.bux.azenkovich.tradingbot.dto.order;

import com.bux.azenkovich.tradingbot.dto.Dto;
import com.bux.azenkovich.tradingbot.enums.TradeDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeOrderDto implements Dto {
    private String productId;
    private BigMoney investingAmount;
    private int leverage;
    @Builder.Default
    private TradeDirection direction = TradeDirection.BUY;
    private String source;
}
