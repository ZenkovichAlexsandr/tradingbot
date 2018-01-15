package com.bux.azenkovich.tradingbot.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author alexsandr
 * @since 09.01.18.
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
    private String webSocketUrl;
    private String baseUrl;
    private String token;
    private String acceptLanguages;
    private String byPostfix;
    private String sellPostfix;
}
