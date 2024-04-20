package github.io.robinhosz.emailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static github.io.robinhosz.emailsender.util.DontEnvUtil.loadDotenv;

@SpringBootApplication
public class EmailSenderApplication {

	public static void main(String[] args) {
		loadDotenv();
		SpringApplication.run(EmailSenderApplication.class, args);

	}

}
