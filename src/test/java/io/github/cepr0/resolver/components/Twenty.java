package io.github.cepr0.resolver.components;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
class Twenty implements Supplier<Integer> {
	@Override
	public Integer get() {
		return 20;
	}
}
