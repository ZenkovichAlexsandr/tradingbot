package com.bux.azenkovich.tradingbot.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Getter
@Setter
public class CommonResponseDto implements Dto {
    private String message;
    private String developerMessage;
    private String errorCode;
}
