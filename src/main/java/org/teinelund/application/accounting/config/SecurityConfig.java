package org.teinelund.application.accounting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                //.antMatchers("/admin/").hasRole("ADMIN")
                //.anyRequest().permitAll()
                //.anyRequest().authenticated()
                //.and()

        http.authorizeRequests().antMatchers("/list").access("hasAnyRole('USER', 'ADMIN')");

        http.authorizeRequests().and().formLogin()
                .loginPage("/login")
                .permitAll()

                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    // create two users, admin and user
    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("U").roles("USER")
                .and()
                .withUser("admin").password("a").roles("ADMIN");
    }
    */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("u").roles("USER")
                .and()
                .withUser("manager").password("m").roles("ADMIN");
        //auth.jdbcAuthentication().dataSource(dataSource)
        //        .usersByUsernameQuery(
        //                "select username,password, enabled from users where username=?")
        //        .authoritiesByUsernameQuery(
        //                "select username, role from user_roles where username=?");
    }
}
