package com.example.restservice;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfigurations {

    @Bean
    @Scope("singleton")
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
    
    /**
     * Redis Configs
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	JedisConnectionFactory jedisConFactory
        = new JedisConnectionFactory();
    	jedisConFactory.setHostName("127.0.0.1");
  		jedisConFactory.setPort(6379);
  		return jedisConFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
	
}
