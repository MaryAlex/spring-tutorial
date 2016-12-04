package com.mine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    // Override this method to configure the HttpSecurity.
    // Typically subclasses should not invoke this method by calling super as it may override their configuration.
    // The default configuration is:
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // Allows restricting access based upon the HttpServletRequest using
                .anyRequest().authenticated() // All request will be redirect to login page if user doesn't logged in
                .and() // The and() method is used to chain together different configuration directives in configure() .
                .formLogin().and() // We say to use Spring's login form. To use you own create login page and write
                // .formLogin().loginPage("/login")
                .httpBasic(); // Configures HTTP Basic authentication.

        // Also there must be something like:
        // .csrf().disable(); To disable csrf.
        // .requiresChannel() method that lets you declare channel requirements for various URL patterns.
        // .requiresChannel().antMatchers("page").requiresSecure(); automatically redirect the request to go over HTTPS .
        // .antMatchers("/").requiresInecure(); we say home page always be sent over HTTP
        // .anyRequest().permitAll(); Allow all users(and whom not log in) see all pages.
        // .antMatchers("/admin").hasRole("ADMIN") // Only users with role ADMIN can see pages on this address
        // .antMatchers("/admin").hasAuthority("ROLE_ADMIN") Same as above
        // And so on. Read docs.
    }

    // Set some propertries to authentication.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Set our userDetailsService. How I understood this... Spring security use it to take user from DB
        auth.userDetailsService(userDetailsService);
        // Password must be encrypted before go to DB. So there we set how encoder. It must implements PasswordEncoder
        //.passwordEncoder(new BCryptPasswordEncoder());
        // To create some custom user that can sign in.
        //.inMemoryAuthentication().withUser("user").password("password").roles("USER")
    }
}
