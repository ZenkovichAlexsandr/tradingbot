package com.bux.azenkovich.tradingbot.integration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
public interface Request<RESPONSE> {
    /**
     * Return http method of the request.
     */
    HttpMethod getHttpMethod();

    /**
     * Return response body type of the request.
     */
    Class<RESPONSE> getResponseBodyType();

    /**
     * Return headers of the request.
     */
    Map<String, String> getHeaders();

    /**
     * Return http entity of the request.
     */
    HttpEntity getEntity();

    /**
     * Return url of the request.
     */
    String getUrl();
}
