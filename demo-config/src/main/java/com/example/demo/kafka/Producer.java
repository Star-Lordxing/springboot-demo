package com.example.demo.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Component
public class Producer {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 发送消息到kafka
     */
    public RecordMetadata sendChannelMess(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic,message);
        RecordMetadata recordMetadata = null;
        try {
            recordMetadata = future.get().getRecordMetadata();
        } catch (InterruptedException|ExecutionException e) {
            e.printStackTrace();
            System.out.println("发送失败");
        }
        System.out.println("发送成功");
        System.out.println("partition:"+recordMetadata.partition());
        System.out.println("offset:"+recordMetadata.offset());
        System.out.println("topic:"+recordMetadata.topic());

        return recordMetadata;
    }
}
