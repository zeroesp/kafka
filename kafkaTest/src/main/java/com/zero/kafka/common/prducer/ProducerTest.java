package com.zero.kafka.common.prducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Date;

@Component
public class ProducerTest {
	//public static void main(String[] args) {
	public boolean produceMessage(int topicGenNum) {
		boolean result = false;
		
		Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer< String, String> kafkaProcuder = null;

        try {
        	kafkaProcuder = new KafkaProducer<String, String>(kafkaProps);
        	for (int i = 0; i < topicGenNum; i++) {
                String msg = "Message " + i + new Date(System.currentTimeMillis());
                kafkaProcuder.send(new ProducerRecord<String, String>("test", msg));
                System.out.println("Sent:" + msg);
                //Thread.sleep(1000);
            }
	        result = true;
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
        	kafkaProcuder.close();
        }
		return result;
	}

}
