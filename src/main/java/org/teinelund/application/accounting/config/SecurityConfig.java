package org.teinelund.application.accounting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity( securedEnabled = true )
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index", "/register", "/js/**",
                        "/css/**",
                        "/img/**").permitAll();

        http.authorizeRequests().antMatchers("/invoice/**").authenticated();
        //http.authorizeRequests().antMatchers("/invoice/**").access("hasAnyRole('USER', 'ADMIN')");
        //http.authorizeRequests().antMatchers("/invoice/**").hasAnyRole("USER", "ADMIN");

        http.authorizeRequests().and().formLogin()
                .loginPage("/login")
                .permitAll()

                .and()
                .logout()
                //.logoutSuccessUrl("/auth/login?logout")
                .logoutSuccessUrl("/index")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select name, password, enabled from acc_user where name=?")
                .authoritiesByUsernameQuery(
                        "select acc_user.name, acc_role.name from acc_user, user_role, acc_role where acc_user.name=? and acc_user.id=user_role.user_id and user_role.role_id=acc_role.id");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
