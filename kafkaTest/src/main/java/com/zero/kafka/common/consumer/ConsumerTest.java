package com.zero.kafka.common.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

@Component
public class ConsumerTest {
	//public static void main(String[] args) {
	public boolean consumerMessage() {
		boolean result = false;
		
		Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test.group");
        
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "latest");
        
        //String config =ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG;
        
        KafkaConsumer<String, String> consumer = null;
        
        try {
        	consumer = new KafkaConsumer<>(props);
	        consumer.subscribe(Arrays.asList("test"));
	        while (true) {
	            ConsumerRecords<String, String> records = consumer.poll(100);
	            for (ConsumerRecord<String, String> record : records) {
	                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	                //consumer.commitSync();
	            }
	            consumer.commitAsync();
	        }
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
        	consumer.close();
        }
		return result;
	}

}
