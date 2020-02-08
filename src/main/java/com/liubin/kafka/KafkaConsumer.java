package com.liubin.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * @author liubin
 * @description 消息消费者
 * @date 2020/2/5 9:34
 */
@Slf4j
@Component
public class KafkaConsumer {

    /**
     * 初始化消息队列，发送之后暂时保存在list中，然后最早头部读取 earliest
     */
    private static final LinkedList<Long> linkedList = new LinkedList<>();
    private static String content = "";

    @KafkaListener(topics = Topic.SIMPLE)
    public void consumer1_1(ConsumerRecord record) {
        log.info("单独消费者消费消息 ,content = {}", record.value());
    }

    /**
     * 分组1 中的消费者1
     */
    @KafkaListener(id = "consumer1-1", groupId = "group1", topicPartitions =
            {@TopicPartition(topic = Topic.SIMPLE, partitions = {"0", "1"})
            })
    public void consumer1_1_1(ConsumerRecord<String, Object> record) {
        log.info("consumer1-1 收到消息,content={}",record.value());
    }

    /**
     * 分组1 中的消费者2
     * @param record
     */
    @KafkaListener(id = "consumer1-2", groupId = "group2", topicPartitions =
            {@TopicPartition(topic = Topic.SIMPLE, partitions = {"2", "3"})
            })
    public void consumer1_2(ConsumerRecord<String, Object> record) {
        log.info("consumer1-2 收到消息,content={}",record.value());
    }

    /**
     *  分组1 中的消费者3
     * @param record
     */
    @KafkaListener(id = "consumer1-3", groupId = "group1", topicPartitions =
            {@TopicPartition(topic = Topic.SIMPLE, partitions = {"1", "2"})
            })
    public void consumer1_3(ConsumerRecord<String, Object> record) {
        log.info("consumer1-3 收到消息,content={}",record.value());
    }

    /**
     * 分组2 中的消费者
     * @param record
     */
    @KafkaListener(id = "consumer2-1", groupId = "group2", topics =Topic.SIMPLE)
    public void consumer2_1(ConsumerRecord<String, Object> record) {
        log.info("consumer2-1 收到消息,content={}",record.value());
    }
}