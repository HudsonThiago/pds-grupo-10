package com.sip.sip;

import com.sip.sip.service.IExplorarProjetosService;
import com.sip.sip.service.RecStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
public class SipApplication {

	public static void run() {
		SpringApplication.run(SipApplication.class);
	}
}
