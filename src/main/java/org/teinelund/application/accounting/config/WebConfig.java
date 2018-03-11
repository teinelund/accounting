package org.teinelund.application.accounting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/login").setViewName("auth/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    //@Bean(name = "dataSource")
    //public DriverManagerDataSource dataSource() {
    //    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    //    driverManagerDataSource.setDriverClassName("com.postgresql.Driver");
    //    driverManagerDataSource.setUrl("jdbc:postgresgl://localhost:5432/accounting");
    //    driverManagerDataSource.setUsername("henrik");
    //    driverManagerDataSource.setPassword("hlPyYz9r");
    //    return driverManagerDataSource;
    //}
}
