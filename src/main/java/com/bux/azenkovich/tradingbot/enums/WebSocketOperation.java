package com.bux.azenkovich.tradingbot.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author alexsandr
 * @since 12.01.18.
 */
@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum WebSocketOperation {
    CONNECTED("connect.connected"),
    FAILED("connect.failed"),
    QUOTE("trading.quote");

    private final String operation;

    /**
     * Convert from JSON.
     *
     * @param operation - operation
     */
    @JsonCreator
    public static WebSocketOperation create(final String operation) {
        for (final WebSocketOperation v: values()) {
            if (v.getOperation().equals(operation)) {
                return v;
            }
        }
        return null;
    }
}
