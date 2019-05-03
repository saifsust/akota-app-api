package com.hungry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackages = { "com.hungry.*" })
@EntityScan(basePackages = { "com.hungry.entities" })
@EnableJpaRepositories(basePackages = { "com.hungry.repositories" })
@EnableTransactionManagement
public class HungryApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(HungryApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("file:/media/saif-sust/WEB_project/hungry/images/");
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean localContrainerEntityManager = new LocalContainerEntityManagerFactoryBean();
		localContrainerEntityManager.setDataSource(dataSource);
		localContrainerEntityManager.setPackagesToScan(new String[] { "com.hungry.entities"});
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		localContrainerEntityManager.setJpaVendorAdapter(vendorAdapter);
		return localContrainerEntityManager;
	}

	@Bean(name = "entityManager")
	public EntityManager getEntityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager getPlatformTransactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}

}
