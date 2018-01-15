package com.bux.azenkovich.tradingbot.bot;

import com.bux.azenkovich.tradingbot.dto.ChangeSubscriptionDto;
import com.bux.azenkovich.tradingbot.dto.order.MakeOrderDto;
import com.bux.azenkovich.tradingbot.dto.order.OrderDto;
import com.bux.azenkovich.tradingbot.dto.order.SellOrderDto;
import com.bux.azenkovich.tradingbot.dto.websocket.WebSocketMessageDto;
import com.bux.azenkovich.tradingbot.properties.BotProperties;
import com.bux.azenkovich.tradingbot.service.TradingService;
import com.bux.azenkovich.tradingbot.websockets.WebSocketConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TradingBot {
    private final BotProperties botProperties;
    private final TradingService tradingService;
    private WebSocketConfiguration webSocketConfiguration;

    private OrderDto order;

    /**
     * Trade function.
     *
     * @param dto     - {@link WebSocketMessageDto} web socket message with price
     * @param session - {@link Session} web socket session
     */
    public void trade(final WebSocketMessageDto dto, final Session session) {
        if (Objects.isNull(dto.getOperation())) {
            log.info("Unsupported operation");
            return;
        }
        switch (dto.getOperation()) {
            case CONNECTED:
                tradingService.subscribe(new ChangeSubscriptionDto(botProperties.getProductId()), session);
                break;
            case FAILED:
                session.close();
                webSocketConfiguration.start();
                break;
            case QUOTE:
                quote(dto);
                break;
            default:
                break;
        }
    }

    private void quote(final WebSocketMessageDto dto) {
        if (Objects.isNull(order)) {
            buy(dto);
        } else {
            sell(dto);
        }
    }

    private void buy(final WebSocketMessageDto dto) {
        final double currentPrice = Double.parseDouble(dto.getBody().getCurrentPrice());

        if (currentPrice <= botProperties.getBuyPrice()) {
            order = tradingService.buy(MakeOrderDto.builder()
                    .productId(dto.getBody().getSecurityId())
                    .investingAmount(botProperties.getInvestingAmount())
                    .leverage(botProperties.getLeverage())
                    .source(botProperties.getSource())
                    .build());
            log.info("Bot bought order price is " + order.getPrice().getAmount() + " "
                    + order.getPrice().getCurrency());
        }
    }

    private void sell(final WebSocketMessageDto dto) {
        final double currentPrice = Double.parseDouble(dto.getBody().getCurrentPrice());
        if (currentPrice <= this.botProperties.getLowerLimitSellPrice()
                || currentPrice >= this.botProperties.getUpperLimitSellPrice()) {
            final SellOrderDto sell = tradingService.sell(order.getPositionId());
            order = null;
            log.info("Bot sold order. Profit is " + sell.getProfitAndLoss().getAmount() + " "
                    + sell.getProfitAndLoss().getCurrency());
        }
    }
}
