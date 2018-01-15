package com.bux.azenkovich.tradingbot.integration;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
public interface RequestExecutorWrapper {
    /**
     * Check response from the API.
     *
     * @param response - response
     */
    <RESPONSE> RESPONSE translate(RESPONSE response);
}
