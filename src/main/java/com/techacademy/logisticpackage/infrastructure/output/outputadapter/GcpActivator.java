package com.techacademy.logisticpackage.infrastructure.output.outputadapter;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class GcpActivator {

    @Bean
    @ServiceActivator(inputChannel = "topic1")
    public PubSubMessageHandler messageSenderTopic1(PubSubTemplate pubSubTemplate){
        return new PubSubMessageHandler(pubSubTemplate,"PubsubDemo") ;
    }

    @Bean
    @ServiceActivator(inputChannel = "topic2")
    public PubSubMessageHandler messageSenderTopic2(PubSubTemplate pubSubTemplate){
        return new PubSubMessageHandler(pubSubTemplate,"Topic2") ;
    }

}
