package wine.travel.java.wine;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import wine.travel.java.wine.config.RibbonConfiguration;

@SpringBootApplication
@RestController
@RibbonClient(
  name = "ping-a-server",
  configuration = RibbonConfiguration.class)
public class WineApplication {
	
	@LoadBalanced
    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
 
    @Autowired
    RestTemplate restTemplate;
 
    @RequestMapping("/server-location")
    public String serverLocation() {
        return this.restTemplate.getForObject(
          "http://ping-server/locaus", String.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(WineApplication.class, args);
	}
	
	@Bean 
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args-> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
			}
		};
	}

}
