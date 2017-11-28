package com.biel.microservice;

import com.biel.microservice.model.AnalyzedUrl;
import com.biel.microservice.model.User;
import com.biel.microservice.repository.AnalyzedUrlRepo;
import com.biel.microservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class MicroServiceApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(MicroServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(5);
		executor.setThreadNamePrefix("UrlExtractorTask- ");
		executor.initialize();
		return executor;
	}
	@Override
	@Transactional
	public void run(String... args) throws Exception {
			log.info("Creating test User");
			User user = new User();
			user.setUsername("test");
			user.setPassword("1234");
			userRepository.save(user);
			log.info("Created user with Id " + userRepository.findByUsername("test").getUuid());

		}
	}

