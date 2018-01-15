package com.bux.azenkovich.tradingbot.service;

import com.bux.azenkovich.tradingbot.dto.ChangeSubscriptionDto;
import com.bux.azenkovich.tradingbot.dto.order.MakeOrderDto;
import com.bux.azenkovich.tradingbot.dto.order.OrderDto;
import com.bux.azenkovich.tradingbot.dto.order.SellOrderDto;
import com.bux.azenkovich.tradingbot.integration.RequestExecutor;
import com.bux.azenkovich.tradingbot.integration.SyncRequest;
import com.bux.azenkovich.tradingbot.properties.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.websocket.api.Session;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Service
@RequiredArgsConstructor
public class TradingServiceImpl implements TradingService {
    private final RequestExecutor requestExecutor;
    private final ApiProperties apiProperties;

    @Override
    public void subscribe(final ChangeSubscriptionDto dto, final Session session) {
        session.getRemote().sendStringByFuture(dto.toString());
    }

    @Override
    public OrderDto buy(final MakeOrderDto dto) {
        return requestExecutor.execute(SyncRequest.<OrderDto>newBuilder()
                .url(apiProperties.getBaseUrl() + apiProperties.getByPostfix())
                .requestMethod(HttpMethod.POST)
                .responseBodyType(OrderDto.class)
                .header(AUTHORIZATION, this.apiProperties.getToken())
                .body(dto)
                .build());
    }

    @Override
    public SellOrderDto sell(final String positionId) {
        return requestExecutor.execute(SyncRequest.<SellOrderDto>newBuilder()
                .url(apiProperties.getBaseUrl() + apiProperties.getSellPostfix() + positionId)
                .requestMethod(HttpMethod.DELETE)
                .responseBodyType(SellOrderDto.class)
                .header(AUTHORIZATION, this.apiProperties.getToken())
                .build());
    }
}
