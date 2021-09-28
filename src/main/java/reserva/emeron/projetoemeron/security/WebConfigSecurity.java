package reserva.emeron.projetoemeron.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity   extends WebSecurityConfigurerAdapter{

@Override //Configurar as solicitações de acesso por HTTP
protected void configure(HttpSecurity http) throws Exception {


				
}


}
