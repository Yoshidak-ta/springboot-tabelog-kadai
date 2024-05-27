package com.example.tabelog_nagoyameshi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.tabelog_nagoyameshi.StringToTimeConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests((requests) -> requests
					.requestMatchers("/css/**", "/images/**", "/js/**", "/stores/**", "/", "/signup/**", "/reset/**", "/reset/token/**", "/?loggedIn", "/stripe/webhook").permitAll()
					.requestMatchers("/prime/**").hasRole("PRIME")
					.requestMatchers("/admin/store/**").hasRole("ADMIN_STORE")
					.requestMatchers("/admin/site/**").hasRole("ADMIN_SITE")
					.anyRequest().authenticated()
					)
			.formLogin((form) -> form
					.loginPage("/")
					.loginProcessingUrl("/")
					.defaultSuccessUrl("/login?loggedIn")
					.failureUrl("/?error")
					.permitAll()
					)
			.logout((logout) -> logout
					.logoutSuccessUrl("/?loggedOut")
					.permitAll()
					)
			.csrf().ignoringRequestMatchers("/stripe/webhook");
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Configuration
	public class WebConfig implements WebMvcConfigurer {
	    private final StringToTimeConverter stringToTimeConverter;

	    public WebConfig(StringToTimeConverter stringToTimeConverter) {
	        this.stringToTimeConverter = stringToTimeConverter;
	    }

	    @Override
	    public void addFormatters(FormatterRegistry registry) {
	        registry.addConverter(stringToTimeConverter);
	    }
	}

}