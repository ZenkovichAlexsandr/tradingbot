package com.bux.azenkovich.tradingbot.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Component
@RequiredArgsConstructor
public class SyncRequestExecutor implements RequestExecutor {
    private final RestTemplate restTemplate;
    private final RequestExecutorWrapper requestExecutorWrapper;

    @Override
    public <RESPONSE> RESPONSE execute(final Request<RESPONSE> request) {
        return requestExecutorWrapper.translate(restTemplate.exchange(request.getUrl(), request.getHttpMethod(),
                request.getEntity(), request.getResponseBodyType()).getBody());
    }
}
