package com.nt.util;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.nt.controller.FindAccountNumber;
import com.nt.model.AccountValidation;
import com.nt.model.Result;

@Component
public class AccountCheckInfo {
	
	@KafkaListener(topics = "${kafka.reuest.topic}", groupId = "${kafka.group.id}")
	@SendTo
	public Result handle(AccountValidation accountValidation) throws Exception {
		
		String accountNoStatus = FindAccountNumber.getAccountNumber(accountValidation.getAcNumber());
	    Result result = new Result();
        result.setName(accountValidation.getAcName());
        result.setResult(accountNoStatus);
    
        return result;
	}
}