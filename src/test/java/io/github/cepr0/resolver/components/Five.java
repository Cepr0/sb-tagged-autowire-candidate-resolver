package io.github.cepr0.resolver.components;

import io.github.cepr0.resolver.Tags;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Tags({"1symbol", "odd"})
@Component
class Five implements Supplier<String> {
	@Override
	public String get() {
		return "5";
	}
}
