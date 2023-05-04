package ar.edu.unq.grupof.desarrollo_app_criptop2p;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesarrolloAppCriptop2pApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesarrolloAppCriptop2pApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
