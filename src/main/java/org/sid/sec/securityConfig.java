package org.sid.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter{
	/**
	 * {noop} pour dire a spring security que je veux pas utiliser bcryp encoder pour le mot de passe donc mon password ne serait pas encodé en memoire
	 */
/*@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	BCryptPasswordEncoder bcpe=getBCPE();
	
	//auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","USER");
	//auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");
	auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("1234")).roles("ADMIN","USER");
	auth.inMemoryAuthentication().withUser("user1").password(bcpe.encode("1234")).roles("USER");
}*/
@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);//si je laisse comme ça ça veut dire que j'utilise encore la configuration par default et le systeme d'authentification par defaut qui est le systeme par les sessions
	http.csrf().disable();//generalement quand on veut faire une authentification stateless il faut primo desactiver le csrf
	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//spring security c'est plus la peine d'utiliser les sessions maintenant c'est une authentification de type stateless
	//http.authorizeRequests().anyRequest().permitAll();
	http.authorizeRequests().antMatchers("/categories/**").hasAuthority("ADMIN");//on verifie d'abord la requete si c'est un admin 
	http.authorizeRequests().antMatchers("/produits/**").hasAuthority("USER");
	http.authorizeRequests().anyRequest().authenticated();//tous le reste des requetes necessite une authentification quelque soit le role
	http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	}
/*@Bean//ça veut dire que au demarrage lorsqu'on appelle la methode getBCPE elle va nous retourner un bean placé en memoire qui pourra etre gerer par le container spring et on peutl'injecter partout dans le code en utilisant l'annotatio autowired et qui on pourra l'injecter dans d'autres parties de l'application
public BCryptPasswordEncoder getBCPE() {
return new BCryptPasswordEncoder();
}*/
}
