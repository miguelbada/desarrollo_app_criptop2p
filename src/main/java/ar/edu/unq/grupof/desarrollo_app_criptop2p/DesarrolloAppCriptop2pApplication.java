package ar.edu.unq.grupof.desarrollo_app_criptop2p;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class DesarrolloAppCriptop2pApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesarrolloAppCriptop2pApplication.class, args);
	}



}
