package com.simformsolutions.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simformsolutions.kafka.model.User;
import com.simformsolutions.kafka.service.Producer;

@RestController
public class KafkaController {

	@Autowired
	Producer producer;
	
	@GetMapping("/post")
	public void sendMeassage(@RequestBody User user)
	{
		producer.publishToTopic(user);
	}
}
