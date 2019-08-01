package io.github.cepr0.resolver.components;

import io.github.cepr0.resolver.Tags;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Tags({"1symbol", "even"})
@Component
class Four implements Supplier<String> {
	@Override
	public String get() {
		return "4";
	}
}
