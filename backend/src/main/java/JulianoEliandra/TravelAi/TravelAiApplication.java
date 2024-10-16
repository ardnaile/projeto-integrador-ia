package JulianoEliandra.TravelAi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "JulianoEliandra.TravelAi")

public class TravelAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelAiApplication.class, args);
	}

}
