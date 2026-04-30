package com.sandip.ibm.mq.monitor.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DLQListener {
    @JmsListener(destination = "DEV.DEAD.LETTER.QUEUE")
    public void consume(String message) {
        try {
            System.err.println("message cleared from DLQ : " + message);
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
}
