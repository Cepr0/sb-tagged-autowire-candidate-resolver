package io.github.cepr0.resolver.components;

import io.github.cepr0.resolver.Tags;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Tags({"1symbol", "even"})
@Component
class Six implements Supplier<Integer> {
	@Override
	public Integer get() {
		return 6;
	}
}
