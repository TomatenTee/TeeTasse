package refugeeApp;

import org.salespointframework.EnableSalespoint;
import org.salespointframework.SalespointSecurityConfiguration;
import org.salespointframework.SalespointWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@EnableSalespoint
public class Application {
	
	private static final String LOGIN_ROUTE = "/startpage";
	
	public static void main (String[] args){
		SpringApplication.run(Application.class, args);
	}
	
	@Configuration
	static class RefugeeAppWebConfiguration extends SalespointWebConfiguration {
		
		@Override //directly routed to the login template without any controller interaction.
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController(LOGIN_ROUTE).setViewName("startpage");
		}
	}
	@Configuration
	static class WebSecurityConfiguration extends SalespointSecurityConfiguration {

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable();

			http.authorizeRequests().antMatchers("/**").permitAll().and().//
					formLogin().loginPage(LOGIN_ROUTE).loginProcessingUrl("/startpage").and(). //
					logout().logoutUrl("/logout").logoutSuccessUrl("/");
		}
	
	}
	
}
