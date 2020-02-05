package com.liubin.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

/**
 * @author liubin
 * @description 消息生产者
 * @date 2020/2/5 9:18
 */
@Slf4j
@Controller
public class KafkaProducer {
//
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
//    @RequestMapping("/kafkaSend")
//    public void send() {
//        String message = UUID.randomUUID().toString();
//        ListenableFuture listenableFuture = kafkaTemplate.send(Topic.SIMPLE,message);
//        listenableFuture.addCallback(
//                o -> log.info("消息发送成功,{}", o.toString()),
//                throwable -> log.info("消息发送失败,{}" + throwable.getMessage())
//        );
//    }
}