package xyz.deepzeafish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
// @SpringBootApplication is same as adding all 3:
// @Configuration, @EnableAutoConfiguration, @ComponentScan
public class Application implements CommandLineRunner {

	@Autowired
	private Environment environment;

	@Autowired
	private DoStuff doStuff;

	// To adjust environment in Eclipse provide environment variable in
	// runtime / debug configurations:
	// spring.profiles.active=devel
	// OR
	// spring.profiles.active=production
	// OR whatever the target environment is
	//
	// All values here are configured in:
	// src/main/resources/application-devel.properties
	// OR
	// src/main/resources/application-production.properties
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.run(args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		String[] activeProfiles = this.environment.getActiveProfiles();
		if(activeProfiles == null || activeProfiles.length == 0){
			throw new RuntimeException("No active profile found! call with: "
					+ "mvn spring-boot:run -Dspring.profiles.active=\"devel\" or "
					+ "mvn spring-boot:run -Dspring.profiles.active=\"production\"");
		}


		doStuff.doSomeStuff();
	}

}