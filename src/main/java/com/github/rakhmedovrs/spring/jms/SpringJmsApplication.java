package com.github.rakhmedovrs.spring.jms;

import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJmsApplication
{
	public static void main(String[] args) throws Exception
	{
		Configuration configuration = new ConfigurationImpl();
		configuration
			.setPersistenceEnabled(false)
			.setJournalDirectory("target/data/journal")
			.setSecurityEnabled(false)
			.addAcceptorConfiguration("invm", "vm://0");

		ActiveMQServer server = ActiveMQServers.newActiveMQServer(configuration);
		server.start();

		SpringApplication.run(SpringJmsApplication.class, args);
	}
}
