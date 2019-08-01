package io.github.cepr0.resolver.components;

import io.github.cepr0.resolver.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Supplier;

@Component
public class TestComponent {

	private final Map<String, Supplier<Integer>> twoSymbolsOrEven;

	private Map<String, Supplier<String>> partingOrOdd;

	public TestComponent(@Tags({"2symbols", "even"}) Map<String, Supplier<Integer>> twoSymbolsOrEven) {
		this.twoSymbolsOrEven = twoSymbolsOrEven;
	}

	@Autowired
	public void setPartingOrOdd(@Tags({"parting", "odd"}) Map<String, Supplier<String>> partingOrOdd) {
		this.partingOrOdd = partingOrOdd;
	}

	public Map<String, Supplier<Integer>> getTwoSymbolsOrEven() {
		return twoSymbolsOrEven;
	}

	public Map<String, Supplier<String>> getPartingOrOdd() {
		return partingOrOdd;
	}
}
