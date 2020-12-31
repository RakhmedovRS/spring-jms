package com.github.rakhmedovrs.spring.jms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * @author RakhmedovRS
 * @created 12/31/2020
 */
@Configuration
public class JmsConfiguration
{
	public static String QUEUE_NAME = "queue";

	@Bean
	public MessageConverter messageConverter()
	{
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");

		return converter;
	}
}
