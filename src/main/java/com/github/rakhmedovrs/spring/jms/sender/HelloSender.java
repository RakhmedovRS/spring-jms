package com.github.rakhmedovrs.spring.jms.sender;

import com.github.rakhmedovrs.spring.jms.config.JmsConfiguration;
import com.github.rakhmedovrs.spring.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author RakhmedovRS
 * @created 12/31/2020
 */
@Component
@RequiredArgsConstructor
public class HelloSender
{
	private final JmsTemplate jmsTemplate;

	@Scheduled(fixedRate = 2000)
	public void sendMessage()
	{
		System.out.println(LocalDateTime.now() +  " Trying to send a hello message");

		HelloWorldMessage helloWorldMessage = HelloWorldMessage
			.builder()
			.id(UUID.randomUUID())
			.message("Hello world")
			.build();

		jmsTemplate.convertAndSend(JmsConfiguration.QUEUE_NAME, helloWorldMessage);

		System.out.println(LocalDateTime.now() +  " Message sent");
	}
}
