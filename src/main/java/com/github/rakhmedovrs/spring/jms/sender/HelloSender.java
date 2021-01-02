package com.github.rakhmedovrs.spring.jms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rakhmedovrs.spring.jms.config.JmsConfiguration;
import com.github.rakhmedovrs.spring.jms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 * @author RakhmedovRS
 * @created 12/31/2020
 */
@Component
@RequiredArgsConstructor
public class HelloSender
{
	private final JmsTemplate jmsTemplate;
	private final ObjectMapper objectMapper;

	@Scheduled(fixedRate = 2000)
	public void sendMessage()
	{
		System.out.println(LocalDateTime.now() + " Trying to send a hello message");

		HelloWorldMessage helloWorldMessage = HelloWorldMessage
			.builder()
			.id(UUID.randomUUID())
			.message("Hello world")
			.build();

		jmsTemplate.convertAndSend(JmsConfiguration.FIRST_QUEUE_NAME, helloWorldMessage);

		System.out.println(LocalDateTime.now() + " Message sent");
	}

	@Scheduled(fixedRate = 2000)
	public void sendAndReceiveMessage() throws JMSException
	{
		HelloWorldMessage helloWorldMessage = HelloWorldMessage
			.builder()
			.id(UUID.randomUUID())
			.message("Hello")
			.build();

		Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfiguration.SECOND_QUEUE_NAME, session -> {
			Message message;
			try
			{
				System.out.println(LocalDateTime.now() + " Trying to send a hello");

				message = session.createTextMessage(objectMapper.writeValueAsString(helloWorldMessage));
				message.setStringProperty("_type", "com.github.rakhmedovrs.spring.jms.model.HelloWorldMessage");
			}
			catch (JsonProcessingException e)
			{
				throw new JMSException(e.getMessage());
			}
			return message;
		});

		System.out.println(LocalDateTime.now() + " Hello sent");
		System.out.println(receivedMessage.getBody(String.class));
	}
}
