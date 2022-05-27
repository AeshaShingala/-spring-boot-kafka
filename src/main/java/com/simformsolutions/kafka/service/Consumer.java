package com.simformsolutions.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.simformsolutions.kafka.model.User;

@Service
public class Consumer {

	@KafkaListener(topics="topic1")
	public void consumeFromTopic(User user)
	{
		System.out.println(user);
		
	}
}
