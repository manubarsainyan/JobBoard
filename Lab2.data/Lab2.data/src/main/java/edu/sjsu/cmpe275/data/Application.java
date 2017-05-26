package edu.sjsu.cmpe275.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.application.ApplicationService;
import edu.sjsu.cmpe275.user.JobSeekerService;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@ComponentScan({"edu.sjsu.cmpe275.lab2.*","edu.sjsu.cmpe275.*"})
@EntityScan("edu.sjsu.cmpe275.lab2.*")
public class Application {

	public static void main(String[] args) {	
		Object[] sources = new Object[2];
        sources[0] =JobSeekerService.class;
        sources[1] =ApplicationService.class;
//        sources[5] =SimpleEmailController.class;
        //sources[1] = Controller2.class;
        //SpringApplication.run(sources, args);
		//SpringApplication.run(PassengerService.class, args);
		SpringApplication.run(Application.class, args);
		
	}
}
