package com.sumit.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
		basePackages = {"com.sumit.repository.ProductRepo"},
		  entityManagerFactoryRef = "secondaryEntityManagerFactory",
		    transactionManagerRef = "secondaryTransactionManager"
)
public class ProductConfig {
	
	
	
	
	 @Primary
	 @Bean(name = "secondaryDataSourceProperties")
	 @ConfigurationProperties("spring.datasource-database-two")
	 public DataSourceProperties secondaryDataSourceProperties() {
	      return new DataSourceProperties();
	 }
	 
	 @Primary
	 @Bean(name = "secondaryDataSource")
	 @ConfigurationProperties("spring.datasource-database-two.configuration")
	 public DataSource secondaryDataSource(@Qualifier("secondaryDataSourceProperties") DataSourceProperties secondaryDataSourceProperties) {
	      return secondaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	 }
	 
	 
	 @Primary
	 @Bean(name = "secondaryEntityManagerFactory")
	 public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
	            EntityManagerFactoryBuilder secondaryEntityManagerFactoryBuilder, @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {

	        Map<String, String> secondaryJpaProperties = new HashMap<>();
	        secondaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	        secondaryJpaProperties.put("hibernate.ddl-auto", "update");

	        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
	        jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
	        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
	        jpaVendorAdapter.setGenerateDdl(true);

	        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
	        emfb.setDataSource(secondaryDataSource);
	        emfb.setPackagesToScan("com.sumit.entities");
	        emfb.setPersistenceUnitName("secondaryDataSource");
	        emfb.setJpaVendorAdapter(jpaVendorAdapter);
	        emfb.setJpaPropertyMap(secondaryJpaProperties);

	        return emfb;
	    }
 
	 @Primary
	 @Bean(name = "secondaryTransactionManager")
	 public PlatformTransactionManager secondaryTransactionManager(
	        @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {
	        return new JpaTransactionManager(secondaryEntityManagerFactory);
	 }
	 
}
