package pl.edu.us.warsztaty.warsztaty.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.naming.AuthenticationException;

@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configurationGlobalSecurity(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().withUser("user").password("test").roles("USER");
        builder.inMemoryAuthentication().withUser("admin").password("test2").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/{name}").hasRole("USER")
                .anyRequest().permitAll()
                .and().csrf().disable()
                .formLogin();
    }

}
