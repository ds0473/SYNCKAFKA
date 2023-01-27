package com.nt.controller;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.AccountValidation;
import com.nt.model.Result;

@RestController
public class KafkaController {

	@Value("${kafka.reuest.topic}")
	private String requestTopic;
	@Autowired
	private ReplyingKafkaTemplate<String, AccountValidation, Result> replyingKafkaTemplate;

	@PostMapping("/get-result")
	public ResponseEntity<Result> getObject(@RequestBody AccountValidation accountValidation)
			throws InterruptedException, ExecutionException {
		
		ProducerRecord<String, AccountValidation> record = new ProducerRecord<String, AccountValidation>(requestTopic, null,
				accountValidation.getAcNumber(), accountValidation);
		RequestReplyFuture<String, AccountValidation, Result> future = replyingKafkaTemplate.sendAndReceive(record);
		
		ConsumerRecord<String, Result> response = future.get();
		System.out.println("Response value is :" + response.value());
		return new ResponseEntity<Result>(response.value(), HttpStatus.OK);
	}
}