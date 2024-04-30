package org.ens.requestservice.rabbitmq;

public interface IRabbitMQProducerService {

    void sendMessage(String message, String routingKey);
}