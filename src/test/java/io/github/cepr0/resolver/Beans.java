package io.github.cepr0.resolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
@ComponentScan("io.github.cepr0.resolver.components")
class Beans {

	@Tags({"greeting", "2symbols", "even"})
	@Bean
	public Supplier<String> hi() {
		return () -> "hi";
	}

	@Tags({"parting", "2symbols", "even"})
	@Bean
	public Supplier<String> by() {
		return () -> "by";
	}

	@Tags({"greeting", "5symbols", "odd"})
	@Bean
	public Supplier<String> hello() {
		return () -> "hello";
	}

	@Tags({"parting", "7symbols", "odd"})
	@Bean
	public Supplier<String> goodbye() {
		return () -> "goodbye";
	}

	@Tags({"other", "5symbols", "odd"})
	@Bean
	public Supplier<String> other() {
		return () -> "other";
	}

	@Bean
	public Supplier<String> untaggedString() {
		return () -> "untagged";
	}

	@Tags({"2symbols", "odd"})
	@Bean
	public Supplier<Integer> eleven() {
		return () -> 11;
	}

	@Tags({"2symbols", "even"})
	@Bean
	public Supplier<Integer> twelve() {
		return () -> 12;
	}

	@Tags({"1symbol", "odd"})
	@Bean
	public Supplier<Integer> one() {
		return () -> 1;
	}

	@Tags({"1symbol", "even"})
	@Bean
	public Supplier<Integer> two() {
		return () -> 2;
	}

	@Bean
	public Supplier<Integer> untaggedNum() {
		return () -> 3;
	}
}
