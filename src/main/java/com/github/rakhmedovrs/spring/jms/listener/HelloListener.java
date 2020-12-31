package com.github.rakhmedovrs.spring.jms.listener;

import com.github.rakhmedovrs.spring.jms.config.JmsConfiguration;
import com.github.rakhmedovrs.spring.jms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

/**
 * @author RakhmedovRS
 * @created 12/31/2020
 */
@Component
public class HelloListener
{
	@JmsListener(destination = JmsConfiguration.QUEUE_NAME)
	public void listen(@Payload HelloWorldMessage helloWorldMessage,
	                   @Headers MessageHeaders header,
	                   Message message)
	{
		System.out.println("A message received -----> " + helloWorldMessage);
	}
}
