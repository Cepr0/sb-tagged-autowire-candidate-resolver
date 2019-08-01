package io.github.cepr0.resolver;

import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutowireConfig {
	@Bean
	public CustomAutowireConfigurer autowireConfigurer(DefaultListableBeanFactory beanFactory) {
		CustomAutowireConfigurer configurer = new CustomAutowireConfigurer();
		beanFactory.setAutowireCandidateResolver(new TaggedAutowireCandidateResolver());
		configurer.postProcessBeanFactory(beanFactory);
		return configurer;
	}
}
