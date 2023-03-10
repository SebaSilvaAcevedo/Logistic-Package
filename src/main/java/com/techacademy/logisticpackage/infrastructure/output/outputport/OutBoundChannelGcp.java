package com.techacademy.logisticpackage.infrastructure.output.outputport;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "myPubSubGateway", defaultRequestChannel = "outboundmessage")
public interface OutBoundChannelGcp {
    @Gateway(requestChannel = "topic1")
    void sendMsgToTopic1(String msg);

    @Gateway(requestChannel = "topic2")
    void sendMsgToTopic2(String msg);

}
