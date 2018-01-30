package com.zero.kafka.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zero.kafka.common.consumer.ConsumerTest;
import com.zero.kafka.common.prducer.ProducerTest;

@Controller
public class CommonController {
	
	@Autowired
	ProducerTest producerTest;
	
	@Autowired
	ConsumerTest consumerTest;
	
	@RequestMapping("/")
    public String index(Model model) {
		System.out.println("index call");
		return "index";
	}
	
	@RequestMapping("/producer")
    public String producer(Model model, String topicName, int topicGenNum) {
		System.out.println("producer call :" + topicName + ", " + topicGenNum);
		producerTest.produceMessage(topicGenNum);
		model.addAttribute("produceResult",topicName + ", (" + topicGenNum + ") produce success");
		return "index";
	}
	
	@RequestMapping("/consumer")
    public String consumer(Model model) {
		System.out.println("consumer call");
		consumerTest.consumerMessage();
		return "index";
	}
	
}
