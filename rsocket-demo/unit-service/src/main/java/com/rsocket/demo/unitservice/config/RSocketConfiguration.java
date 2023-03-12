package com.rsocket.demo.unitservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeType;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class RSocketConfiguration {

    @Bean
    public RSocketStrategies rSocketStrategies() {
        return RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
                .decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
                .build();
    }

    @Bean
    public RSocketRequester rSocketRequester(RSocketStrategies rSocketStrategies) {
        return RSocketRequester.builder()
                .rsocketConnector(r -> r.reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2))))
                .rsocketStrategies(rSocketStrategies)
                .dataMimeType(MediaType.APPLICATION_JSON)
                .metadataMimeType(MimeType.valueOf("message/x.rsocket.routing.v0"))
                .tcp("localhost", 7000);
    }
}
