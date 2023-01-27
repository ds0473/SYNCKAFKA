package com.nt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import com.nt.model.AccountValidation;
import com.nt.model.Result;


@Configuration
public class KafkaConfig {
	@Value("${kafka.group.id}")
	private String groupId;
	@Value("${kafka.reply.topic}")
	private String replyTopic;

	@Bean
	public ReplyingKafkaTemplate<String, AccountValidation, Result> replyingKafkaTemplate(
			ProducerFactory<String, AccountValidation> pf,
			ConcurrentKafkaListenerContainerFactory<String, Result> factory) {
		
		ConcurrentMessageListenerContainer<String, Result> replyContainer = factory.createContainer(replyTopic);
		replyContainer.getContainerProperties().setMissingTopicsFatal(false);
		replyContainer.getContainerProperties().setGroupId(groupId);
		
		return new ReplyingKafkaTemplate<String, AccountValidation, Result>(pf, replyContainer);
	}

	@Bean
	public KafkaTemplate<String, Result> replyTemplate(ProducerFactory<String, Result> pf,
			ConcurrentKafkaListenerContainerFactory<String, Result> factory) {
		
		KafkaTemplate<String, Result> kafkaTemplate = new KafkaTemplate<String, Result>(pf);
		factory.getContainerProperties().setMissingTopicsFatal(false);
		factory.setReplyTemplate(kafkaTemplate);
		
		return kafkaTemplate;
	}
}