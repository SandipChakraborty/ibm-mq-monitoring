package com.sandip.ibm.mq.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableJms
@EnableRetry
public class IbmMqMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbmMqMonitoringApplication.class, args);
	}

}
