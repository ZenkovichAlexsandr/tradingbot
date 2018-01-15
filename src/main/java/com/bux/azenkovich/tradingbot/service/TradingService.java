package com.bux.azenkovich.tradingbot.service;

import com.bux.azenkovich.tradingbot.dto.ChangeSubscriptionDto;
import com.bux.azenkovich.tradingbot.dto.order.MakeOrderDto;
import com.bux.azenkovich.tradingbot.dto.order.OrderDto;
import com.bux.azenkovich.tradingbot.dto.order.SellOrderDto;
import org.eclipse.jetty.websocket.api.Session;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
public interface TradingService {
    /**
     * Subscribe to the web socket chanel with a price.
     *
     * @param dto     - {@link ChangeSubscriptionDto} change subscription data
     * @param session - web socket session
     */
    void subscribe(ChangeSubscriptionDto dto, Session session);

    /**
     * Buy order.
     *
     * @param dto - {@link MakeOrderDto} new order
     * @return new order
     */
    OrderDto buy(MakeOrderDto dto);

    /**
     * Sell order.
     *
     * @param positionId - position id
     * @return order
     */
    SellOrderDto sell(String positionId);
}
