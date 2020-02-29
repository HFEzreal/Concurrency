package com.liubin.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author liubin
 * @description 消息生产者
 * @date 2020/2/5 9:18
 */
@Slf4j
//@RestController
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/kafkaSend")
    public void send(String topic,String message) {
        ListenableFuture listenableFuture = kafkaTemplate.send(topic, message);
        listenableFuture.addCallback(
                o -> log.info("消息发送成功,{}", o.toString()),
                throwable -> log.info("消息发送失败,{}" + throwable.getMessage())
        );
    }

    @RequestMapping("/kafkaSendToPartition")
    public void send(String topic, String message, Integer partition) {
        ListenableFuture listenableFuture = kafkaTemplate.send(topic, partition, "mykey", message);
        listenableFuture.addCallback(
                o -> log.info("消息发送成功,{}", o.toString()),
                throwable -> log.info("消息发送失败,{}" + throwable.getMessage())
        );
    }
}