package ru.cynteka.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import ru.cynteka.springboot.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"ru.cynteka.springboot"})
public class CyntekaSupplyProgram {

	public static void main(String[] args) {
		SpringApplication.run(CyntekaSupplyProgram.class, args);
	}
}
