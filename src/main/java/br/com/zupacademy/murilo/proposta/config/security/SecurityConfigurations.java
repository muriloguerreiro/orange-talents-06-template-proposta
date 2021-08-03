package br.com.zupacademy.murilo.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Profile("prod")
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests(authorize ->
        		authorize
        			//.antMatchers(HttpMethod.POST,"/propostas").hasAuthority("SCOPE_propostas")
        			//.antMatchers(HttpMethod.GET,"/propostas/**").hasAuthority("SCOPE_propostas")
        			//.antMatchers("/biometrias/**").hasAuthority("SCOPE_biometrias")
        			//.antMatchers(HttpMethod.GET,"/actuator/**").hasAuthority("SCOPE_actuator")
        			.anyRequest().authenticated()
        	)
        	.cors()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
