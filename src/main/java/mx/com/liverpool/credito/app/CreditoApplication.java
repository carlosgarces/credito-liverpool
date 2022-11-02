package mx.com.liverpool.credito.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
	

@SpringBootApplication(scanBasePackages = { "mx.com.liverpool.credito.rest" })
@Import({
		mx.com.liverpool.credito.servicios.config.ServiceConfig.class
	})
public class CreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditoApplication.class, args);
	}
	

}
