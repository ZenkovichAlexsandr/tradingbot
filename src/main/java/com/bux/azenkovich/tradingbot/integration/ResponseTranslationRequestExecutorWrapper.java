package com.bux.azenkovich.tradingbot.integration;

import com.bux.azenkovich.tradingbot.exceptions.ApiException;
import com.bux.azenkovich.tradingbot.dto.CommonResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Slf4j
@Component
public class ResponseTranslationRequestExecutorWrapper implements RequestExecutorWrapper {
    @Override
    public <RESPONSE> RESPONSE translate(final RESPONSE response) {
        if (response instanceof CommonResponseDto) {
            final CommonResponseDto dto = (CommonResponseDto) response;
            if (Objects.nonNull(dto.getErrorCode())) {
                log.error("Error when send request to the api", dto.getDeveloperMessage());
                throw new ApiException(dto.getErrorCode(), dto.getDeveloperMessage());
            }
        }
        return response;
    }
}
