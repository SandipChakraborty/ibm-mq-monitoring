package com.sandip.ibm.mq.monitor.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final JmsTemplate jmsTemplate;

    public MessageService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String sendMessage(String message) {
        jmsTemplate.convertAndSend("DEV.QUEUE.1", message);
        return "Message sent: " + message;
    }

    @Async
    public void sendMessages(int count) {
        for (int i = 0; i < count; i++) {
            jmsTemplate.convertAndSend("DEV.QUEUE.1", "Message " + i);
        }
    }

    @Async
    public void sendMessages(int count, String message) {
        for (int i = 0; i < count; i++) {
            jmsTemplate.convertAndSend("DEV.QUEUE.1", message + i);
        }
    }
}
