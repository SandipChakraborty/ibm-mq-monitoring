package com.sandip.ibm.mq.monitor.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessor {

    private final Timer timer;

    public MessageProcessor(MeterRegistry registry) {
        this.timer = registry.timer("mq_processing_time");
    }

    @Retryable(
            retryFor = Exception.class,
            maxAttempts = 1,
            backoff = @Backoff(delay = 2)
    )
    public void process(String message) {
        timer.record(() -> {
            doProcess(message);
        });
    }

    private void doProcess(String message) {
        if (message.contains("fail")) {
            throw new RuntimeException("Simulated failure");
        }
        System.out.println("Processing " + message);
    }

    @Recover
    public void recover(Exception e, String message) {
        throw new RuntimeException("Retries exhausted for: " + message, e);
    }
}