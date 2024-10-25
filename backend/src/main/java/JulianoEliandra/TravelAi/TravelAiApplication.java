package JulianoEliandra.TravelAi;

//import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "JulianoEliandra.TravelAi")

public class TravelAiApplication {

	public static void main(String[] args) {
//		Dotenv dotenv = Dotenv.load();
		SpringApplication.run(TravelAiApplication.class, args);
	}

}
