package ru.lakeevda.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@EnableJpaRepositories(basePackages = "ru.lakeevda.productservice.repository")
@PropertySource("classpath:h2.properties")
@EnableTransactionManagement
public class H2JpaConfig {

    public static final String PROPERTY_DB_DRIVER = "db.driver";
    public static final String PROPERTY_DB_URL = "db.url";
    public static final String PROPERTY_DB_USERNAME = "db.username";
    public static final String PROPERTY_DB_PASSWORD = "db.password";

    @Bean
    DataSource dataSource(Environment environment) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource() {
            public Connection getConnection(String username, String password) throws SQLException {
                return super.getConnection();
            }
        };

        dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_DB_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PROPERTY_DB_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_DB_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_DB_PASSWORD));

        return dataSource;
    }
}
