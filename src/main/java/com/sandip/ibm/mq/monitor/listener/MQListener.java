package com.sandip.ibm.mq.monitor.listener;

import com.sandip.ibm.mq.monitor.service.MessageProcessor;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MQListener {

    private final MessageProcessor processor;
    private final Counter successCounter;
    private final Counter failureCounter;
    private final Timer timer;
    private final JmsTemplate jmsTemplate;

    public MQListener(MessageProcessor processor, MeterRegistry registry, JmsTemplate jmsTemplate) {
        this.processor = processor;
        this.timer = registry.timer("mq_processing_time");
        this.jmsTemplate = jmsTemplate;

        this.successCounter = Counter.builder("mq_messages_success_total")
                .register(registry);

        this.failureCounter = Counter.builder("mq_messages_failed_total")
                .register(registry);
    }

    @JmsListener(destination = "DEV.QUEUE.1")
    public void consume(String message) {
        try {

            timer.record(() -> {
                processor.process(message); // 👈 latency measured here
            });

            successCounter.increment();

        } catch (Exception e) {
            failureCounter.increment();

            // 👇 Send to error queue
            jmsTemplate.convertAndSend("DEV.DEAD.LETTER.QUEUE", message);

            System.err.println("Message moved to DLQ: " + message);
        }
    }
}
