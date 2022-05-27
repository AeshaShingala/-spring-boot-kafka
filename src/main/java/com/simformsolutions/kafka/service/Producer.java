package com.simformsolutions.kafka.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.simformsolutions.kafka.model.User;

@Service
public class Producer {
//	public final static String TOPIC="topic1";
	
	@Autowired
	NewTopic topic;
	
	@Autowired
	public KafkaTemplate<String, User> kafkaTemplate;
	
	public void publishToTopic(User user)
	{
		System.out.println("Publishing...");
		ListenableFuture<SendResult<String, User>> future=kafkaTemplate.send(topic.name(), user);
		future.addCallback(new ListenableFutureCallback<SendResult<String, User>>()
		{
		      @Override
		      public void onSuccess(SendResult<String, User> result) {
		        System.out.println(user+" delivered with partition : "+ result.getRecordMetadata().partition()+ " and offset "+result.getRecordMetadata().offset());
		      }
		  
		      @Override
		      public void onFailure(Throwable ex) {
		    	  System.out.println("Unable to deliver message "+user + " . "+ex.getMessage());
		      }
		    });
	}

}
