package mx.com.liverpool.credito.servicios.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(includeFilters = { @Filter(type = FilterType.ANNOTATION, value = Service.class),
		@Filter(type = FilterType.ANNOTATION, value = Component.class) }, basePackages = {
				"mx.com.liverpool.credito.servicios.impl", 
				"mx.com.liverpool.credito.utilerias.impl" 
				})
public class ServiceConfig {

}
