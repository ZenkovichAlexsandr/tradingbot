package com.bux.azenkovich.tradingbot.integration;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
public interface RequestExecutor {
    /**
     * Execute request.
     *
     * @param request - {@link Request} request
     */
    <RESPONSE> RESPONSE execute(Request<RESPONSE> request);
}
