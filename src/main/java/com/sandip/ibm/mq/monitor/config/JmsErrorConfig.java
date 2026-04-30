package com.sandip.ibm.mq.monitor.config;

import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsErrorConfig {

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
            ConnectionFactory connectionFactory,
            JmsTemplate jmsTemplate
    ) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);

        // Retry config
        factory.setSessionTransacted(true);
        factory.setErrorHandler(t -> {
            System.err.println("Listener error: " + t.getMessage());
        });

        return factory;
    }
}
