package com.kimuohs.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

	@KafkaListener(topics = "demo", groupId = "g1")
	void listener(String data) {
		System.out.println("Listener Received: "+ data + "😊");
	}
}
