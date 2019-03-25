package com.zero.kafka.common.prducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RecordMetadata;
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
        
        kafkaProps.put("acks", "all");
        kafkaProps.put("retries", "50");
        kafkaProps.put("max.in.flight.requests.per.connection", "5");
        kafkaProps.put("enable.idempotence", "true");
        
        //String config = ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG;
        
        Producer< String, String> kafkaProcuder = null;
        
        String[] ips = {"101","12","131","143","112","51","74","36","29","181"};
        String[] hosts = {"poc","stg","dev","op","test"};

        try {
        	kafkaProcuder = new KafkaProducer<String, String>(kafkaProps);
        	for (int i = 0; i < topicGenNum; i++) {
                //String msg = "Message " + i + new Date(System.currentTimeMillis());
        	    String msg = "Message " + i + "," + hosts[(int)Math.floor(Math.random()*4.9)] + " "
        	                 + ips[(int)Math.floor(Math.random()*9.9)] + "." + ips[(int)Math.floor(Math.random()*9.9)] + "."
        	                 + ips[(int)Math.floor(Math.random()*9.9)] + "." + ips[(int)Math.floor(Math.random()*9.9)] ;
                
        	    //async
        	    kafkaProcuder.send(new ProducerRecord<String, String>("test", msg));
                //sync
                //RecordMetadata res = kafkaProcuder.send(new ProducerRecord<String, String>("test", msg)).get();
        	    
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
