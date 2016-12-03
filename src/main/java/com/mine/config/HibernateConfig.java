package com.mine.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement // If you have some questions about transaction, please,
// read about it and why they needed when you work with SessionFactories. Also you can ask me personally.
@ComponentScan("com.mine")
@PropertySource(value = "classpath:application.properties")
public class HibernateConfig {

    // For working with property file
    @Autowired
    private Environment environment;

    // To connect with BD. Names of method says for themselves.
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(this.environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(this.environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(this.environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    // Initialization of SessionFactory. If something doesn't clear here, please, read about Session Factory at all.
    @Bean
    @Autowired
    public SessionFactory sessionFactory(DataSource dataSource) {
        // First we create builder and add setting to it, so after we can create Session Factory from it.
        // dataSource is for what DB we create this factory.
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        // scanPackages:
        // Perform Spring-based scanning for entity classes, registering them as annotated classes with this Configuration.
        // addProperties:
        // Adds the incoming properties to the internal properties structure,
        // as long as the internal structure does not already contain an entry for the given key.
        builder.scanPackages("com.mine.model").addProperties(this.getHibernateProperties());

        // So in the end we build SessionFactory.
        return builder.buildSessionFactory();
    }

    // Creating HibernateTransactionManager so Spring can start and commit/rollback transactions for you automatically.
    @Bean
    @Autowired
    public HibernateTransactionManager txManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    // Creating Properties to add them in SessionFactory
    private Properties getHibernateProperties() {
        Properties properties = new Properties(); // So we create Properties
        // Write all SQL statements to console.
        // This is an alternative to setting the log category org.hibernate.SQL to debug.
        properties.put("hibernate.show_sql", "true");
        // Pretty print the SQL in the log and console.
        properties.put("hibernate.format_sql", "true");
        // The classname of a Hibernate org.hibernate.dialect.Dialect which allows Hibernate to generate SQL optimized
        // for a particular relational database.
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        // There are also other properties. Some list of it you can find there:
        // https://docs.jboss.org/hibernate/stable/core.old/reference/en/html/configuration-optional.html
        // 04.12.2016(D.M.Y.)
        return properties; // So we create Properties
    }

}
