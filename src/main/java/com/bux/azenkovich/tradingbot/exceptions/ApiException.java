package com.bux.azenkovich.tradingbot.exceptions;

import lombok.Getter;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Getter
public class ApiException extends RuntimeException {
    private String code;

    public ApiException(final String code, final String message) {
        super(message);
        this.code = code;
    }
}
