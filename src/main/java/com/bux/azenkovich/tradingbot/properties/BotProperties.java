package com.bux.azenkovich.tradingbot.properties;

import com.bux.azenkovich.tradingbot.dto.order.BigMoney;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author alexsandr
 * @since 13.01.18.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "bot")
public class BotProperties {
    private String productId;
    private double buyPrice;
    private double upperLimitSellPrice;
    private double lowerLimitSellPrice;
    private int leverage;
    private String source;
    private BigMoney investingAmount;
}
