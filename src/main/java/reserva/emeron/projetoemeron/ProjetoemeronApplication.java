package reserva.emeron.projetoemeron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EntityScan(basePackages ="reserva.emeron.projetoemeron.model")
@ComponentScan(basePackages = {"reserva.*"})
@EnableJpaRepositories(basePackages = {"reserva.emeron.projetoemeron.repository"})
@EnableTransactionManagement
public class ProjetoemeronApplication  {

	public static void main(String[] args) {
		
		SpringApplication.run(ProjetoemeronApplication.class, args);
		
		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String result = encoder.encode("123");
//		System.out.println(result);
	}
	
	
	
	
	

	
	
	



}
