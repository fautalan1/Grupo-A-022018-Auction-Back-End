package appllication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"entity"})
public class ApplicationRestFull {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRestFull.class, args);
	}
}
