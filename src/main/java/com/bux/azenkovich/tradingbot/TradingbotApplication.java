package com.bux.azenkovich.tradingbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Collections.singletonList;


@SpringBootApplication
@EnableAutoConfiguration
public class TradingbotApplication {
    public static void main(final String[] args) {
        SpringApplication.run(TradingbotApplication.class, args);
    }

    /**
     * Configure rest template.
     *
     * @param converter - {@link MappingJackson2HttpMessageConverter} message parser
     */
    @Bean
    public RestTemplate restTemplate(final MappingJackson2HttpMessageConverter converter) {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConvertersList(converter));
        return restTemplate;
    }

    private List<HttpMessageConverter<?>> getMessageConvertersList(
            final MappingJackson2HttpMessageConverter converter) {
        return new HttpMessageConverters(singletonList(converter)).getConverters();
    }
}
