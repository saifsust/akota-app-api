package com.hungry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hungry.rabbitmq.util.Queues;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackages = { "com.hungry.*" })
@EntityScan(basePackages = { "com.hungry.entities" })
@EnableJpaRepositories(basePackages = { "com.hungry.repositories" })
@EnableTransactionManagement
@EnableRabbit
public class HungryApplication extends SpringBootServletInitializer
		implements WebMvcConfigurer, RabbitListenerConfigurer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(HungryApplication.class);
	}

	/**
	 * 
	 * RabbitMQ queues and exchange binding
	 */

	@Bean
	public Queue adminQueue() {
		return QueueBuilder.durable(Queues.ADMIN).build();
	}

	@Bean
	public Queue cookerQueue() {
		return QueueBuilder.durable(Queues.COOKER).build();
	}

	@Bean
	public Queue deleveryQueue() {
		return QueueBuilder.durable(Queues.DELEVERY).build();
	}

	@Bean
	public Queue managerQueue() {
		return QueueBuilder.durable(Queues.MANAGER).build();
	}

	@Bean
	public FanoutExchange orderFanoutExchnage() {
		return (FanoutExchange) ExchangeBuilder.fanoutExchange(Queues.EXCHNAGE).build();
	}

	@Bean
	public Binding adminBinding() {
		return BindingBuilder.bind(adminQueue()).to(orderFanoutExchnage());
	}

	@Bean
	public Binding cookerBinding() {
		return BindingBuilder.bind(cookerQueue()).to(orderFanoutExchnage());
	}

	@Bean
	public Binding managerBinding() {
		return BindingBuilder.bind(managerQueue()).to(orderFanoutExchnage());
	}

	@Bean
	public Binding deleveryBinding() {
		return BindingBuilder.bind(deleveryQueue()).to(orderFanoutExchnage());
	}

	/**
	 * 
	 * RabbitMQ queues and exchange binding
	 */

	/**
	 * RabbitMQ config beans
	 */

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		return template;
	}

	@Bean
	public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public DefaultMessageHandlerMethodFactory rabbitHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(mappingJackson2MessageConverter());
		return factory;
	}

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(rabbitHandlerMethodFactory());
	}

	/**
	 * RabbitMQ config beans end
	 */

	public static void main(String[] args) {
		SpringApplication.run(HungryApplication.class, args);
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean localContrainerEntityManager = new LocalContainerEntityManagerFactoryBean();
		localContrainerEntityManager.setDataSource(dataSource);
		localContrainerEntityManager.setPackagesToScan(new String[] { "com.hungry.entities" });
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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("file:/media/saif-sust/WEB_project/hungry/images/");
	}

}
