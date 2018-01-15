package com.bux.azenkovich.tradingbot.integration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Getter
@RequiredArgsConstructor
public final class SyncRequest<RESPONSE> implements Request<RESPONSE> {
    private final HttpMethod httpMethod;
    private final Class<RESPONSE> responseBodyType;
    private final Map<String, String> headers;
    private final Object body;
    private final String url;

    @Override
    public HttpEntity getEntity() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        this.headers.forEach(httpHeaders::add);
        return new HttpEntity<>(body, httpHeaders);
    }

    public static <RESPONSE> Builder<RESPONSE> newBuilder() {
        return new Builder<>();
    }

    private SyncRequest(final Builder<RESPONSE> builder) {
        this.httpMethod = builder.httpMethod;
        this.responseBodyType = builder.responseBodyType;
        this.headers = builder.headers;
        this.body = builder.body;
        this.url = builder.url;
    }

    public static final class Builder<RESPONSE> {
        private HttpMethod httpMethod;
        private Class<RESPONSE> responseBodyType;
        private Object body;
        private Map<String, String> headers = new HashMap<>();
        private String url;

        public SyncRequest<RESPONSE> build() {
            return new SyncRequest<>(this);
        }

        public Builder<RESPONSE> requestMethod(final HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public Builder<RESPONSE> responseBodyType(final Class<RESPONSE> responseBodyType) {
            this.responseBodyType = responseBodyType;
            return this;
        }

        public Builder<RESPONSE> body(final Object value) {
            this.body = value;
            return this;
        }

        public Builder<RESPONSE> header(final String key, final String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder<RESPONSE> url(final String url) {
            this.url = url;
            return this;
        }
    }

}
