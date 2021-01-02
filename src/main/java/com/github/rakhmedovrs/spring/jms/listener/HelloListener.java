package com.github.rakhmedovrs.spring.jms.listener;

import com.github.rakhmedovrs.spring.jms.config.JmsConfiguration;
import com.github.rakhmedovrs.spring.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author RakhmedovRS
 * @created 12/31/2020
 */
@Component
@RequiredArgsConstructor
public class HelloListener
{
	private final JmsTemplate jmsTemplate;

	@JmsListener(destination = JmsConfiguration.FIRST_QUEUE_NAME)
	public void listen(@Payload HelloWorldMessage helloWorldMessage,
	                   @Headers MessageHeaders header,
	                   Message message)
	{
		System.out.println("A message received -----> " + helloWorldMessage);
	}

	@JmsListener(destination = JmsConfiguration.SECOND_QUEUE_NAME)
	public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
	                           @Headers MessageHeaders header,
	                           Message message) throws JMSException
	{
		HelloWorldMessage payLoadMessage = HelloWorldMessage
			.builder()
			.id(UUID.randomUUID())
			.message("world")
			.build();

		jmsTemplate.convertAndSend(message.getJMSReplyTo(), payLoadMessage);
	}
}
