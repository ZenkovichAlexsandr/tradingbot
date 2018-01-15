package com.bux.azenkovich.tradingbot.dto.order;

import com.bux.azenkovich.tradingbot.dto.CommonResponseDto;
import com.bux.azenkovich.tradingbot.enums.TradeDirection;
import com.bux.azenkovich.tradingbot.enums.TradeType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Setter
@Getter
public class OrderDto extends CommonResponseDto {
    private String id;
    private String positionId;
    private OrderProductDto product;
    private BigMoney investingAmount;
    private BigMoney price;
    private int leverage;
    private TradeDirection direction;
    private TradeType type;
    private long dateCreated;
}
