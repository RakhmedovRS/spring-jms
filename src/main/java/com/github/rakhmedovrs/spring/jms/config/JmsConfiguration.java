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
	public static final String FIRST_QUEUE_NAME = "queue";
	public static final String SECOND_QUEUE_NAME = "replayQueue";

	@Bean
	public MessageConverter messageConverter()
	{
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");

		return converter;
	}
}
