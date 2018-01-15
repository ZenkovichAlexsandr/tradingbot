package com.bux.azenkovich.tradingbot.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author alexsandr
 * @since 12.01.18.
 */
@AllArgsConstructor
@NoArgsConstructor
public class ChangeSubscriptionDto implements Dto {
    private String from;
    private String to;

    public ChangeSubscriptionDto(final String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "{"
                + "\"subscribeTo\": [\"trading.product." + this.to + "\"],"
                + "\"unsubscribeFrom\": [\"trading.product." + this.from + "\"]"
                + "}";
    }
}
