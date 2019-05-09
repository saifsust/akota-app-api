package com.hungry;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hungry.configs.ApplicationConfigReader;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackages = { "com.hungry.*" })
@EntityScan(basePackages = { "com.hungry.entities" })
@EnableJpaRepositories(basePackages = { "com.hungry.repositories" })
@EnableTransactionManagement
@EnableRabbit
@EnableScheduling
public class HungryApplication extends SpringBootServletInitializer
		implements WebMvcConfigurer, RabbitListenerConfigurer {

	public static final String topicExchangeName = "spring-boot-exchange2";

	public static final String queueName = "spring-boot";

	public static final String queueName1 = "spring-boot1";

	@Autowired
	private ApplicationConfigReader ApplicationConfigReader;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(HungryApplication.class);
	}

	/**
	 * RabbitMQ Config start
	 */

	/* This bean is to read the properties file configs */
	@Bean
	public ApplicationConfigReader applicationConfig() {
		return new ApplicationConfigReader();
	}

	@Bean
	public Queue OrderQueue() {
		return QueueBuilder.durable(queueName).build();
	}

	@Bean
	public Queue OrderQueue2() {
		return QueueBuilder.durable(queueName1).build();
	}

	/*
	 * public Queue deadLetterQueue() { return
	 * QueueBuilder.durable(QUEUE_DEAD_ORDERS); }
	 */

	@Bean
	public FanoutExchange orderExchnage() {
		return (FanoutExchange) ExchangeBuilder.fanoutExchange(topicExchangeName).build();
	}

	@Bean
	public Binding orderBinding() {
		return BindingBuilder.bind(OrderQueue()).to(orderExchnage());
	}

	@Bean
	public Binding order1Binding() {
		return BindingBuilder.bind(OrderQueue2()).to(orderExchnage());
	}

	@Bean
	public Binding order2Binding() {
		return BindingBuilder.bind(getApp1Queue()).to(orderExchnage());
	}

	/**
	 * App1 Config
	 */
	@Bean
	public TopicExchange getApp1Exchange() {

		return new TopicExchange(getApplicationConfigReader().getApp1Exchange());
	}

	@Bean
	public Queue getApp1Queue() {
		return new Queue(getApplicationConfigReader().getApp1Queue());
	}

	@Bean
	public Binding declareBindingApp1() {
		return BindingBuilder.bind(getApp1Queue()).to(getApp1Exchange())
				.with(getApplicationConfigReader().getApp1RoutingKey());
	}

	@Bean
	public List<Declarable> fanoutBinding() {

		// Queue fanoutQueue1 = new Queue("fanout.queue1", false); //Queue
		// fanoutQueue2 = new Queue("fanout.queue2", false);
		FanoutExchange fanoutExchange = new FanoutExchange("fanout.exchange");

		return Arrays.asList(getApp1Queue(), getApp2Queue(), fanoutExchange,
				BindingBuilder.bind(getApp1Queue()).to(fanoutExchange));
	}

	/**
	 * App1 Config end
	 */

	/**
	 * App2 Config
	 */

	@Bean
	public TopicExchange getApp2Exchange() {
		return new TopicExchange(getApplicationConfigReader().getApp2Exchange());
	}

	@Bean
	public Queue getApp2Queue() {
		return new Queue(getApplicationConfigReader().getApp2Queue());
	}

	@Bean
	public Binding declareApp2Binding() {
		return BindingBuilder.bind(getApp2Queue()).to(getApp2Exchange())
				.with(getApplicationConfigReader().getApp2RoutingKey());
	}

	/**
	 * App2 config end
	 */

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		return rabbitTemplate;
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}

	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}

	public ApplicationConfigReader getApplicationConfigReader() {
		return ApplicationConfigReader;
	}

	public void setApplicationConfigReader(ApplicationConfigReader applicationConfigReader) {
		ApplicationConfigReader = applicationConfigReader;
	}

	/**
	 * RabbitMQ config END
	 * 
	 * @param args
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
