package com.aluracursos.apiBooks;

import com.aluracursos.apiBooks.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiBooksApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiBooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraMenu();
	}
}
