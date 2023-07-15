package com.sip.sip.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sip.sip"})
public class SipApplication {

	public static void run() {
		SpringApplication.run(SipApplication.class);
	}
}
