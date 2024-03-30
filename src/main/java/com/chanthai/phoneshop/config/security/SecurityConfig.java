package com.chanthai.phoneshop.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.chanthai.phoneshop.config.security.PermissionEnum.*;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.POST,"/brands").hasAnyAuthority(BRAND_WRITE.getDescription())
//                .antMatchers(HttpMethod.GET,"/brands").hasAnyAuthority(BRAND_READ.getDescription())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
         UserDetails user = User.builder()
                .username("thai")
                .password(passwordEncoder.encode("thai123"))
                 .authorities(RoleEnum.ADMIN.getAuthorities())
                .build();

         UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user);
        return userDetailsService;
    }
}
