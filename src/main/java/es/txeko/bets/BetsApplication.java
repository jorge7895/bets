package es.txeko.bets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Bets",
                version = "1.0",
                contact = @Contact(
                        name = "Jorge",
                        email = ""
                )
        )
)
public class BetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetsApplication.class, args);
	}

}
