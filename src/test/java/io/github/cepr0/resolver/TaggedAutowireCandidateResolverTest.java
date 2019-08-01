package io.github.cepr0.resolver;

import io.github.cepr0.resolver.components.TestComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AutowireConfig.class, Beans.class})
public class TaggedAutowireCandidateResolverTest {

	@Autowired
	private Map<String, Supplier<String>> stringSuppliers;

	@Autowired
	private Map<String, Supplier<Integer>> intSuppliers;

	@Autowired
	@Tags({"greeting", "other"})
	private Map<String, Supplier<String>> greetingOrOther;

	@Autowired
	@Tags({"1symbol", "odd"})
	private Map<String, Supplier<Integer>> oneSymbolOrOdd;

	@Autowired
	private TestComponent testComponent;

	@Test
	public void stringSuppliers() {
		assertThat(stringSuppliers).hasSize(9);
	}

	@Test
	public void intSuppliers() {
		assertThat(intSuppliers).hasSize(7);
	}

	@Test
	public void greetingsOrOther() {
		assertThat(greetingOrOther)
				.hasSize(3)
				.containsOnlyKeys("hi", "hello", "other");

		assertThat(greetingOrOther.get("hi").get()).isEqualTo("hi");
		assertThat(greetingOrOther.get("hello").get()).isEqualTo("hello");
		assertThat(greetingOrOther.get("other").get()).isEqualTo("other");
	}

	@Test
	public void singleSymbolOrOdd() {
		assertThat(oneSymbolOrOdd)
				.hasSize(4)
				.containsOnlyKeys("eleven", "one", "two", "six");
	}

	@Test
	public void testComponent() {
		assertThat(testComponent.getTwoSymbolsOrEven())
				.hasSize(4)
				.containsOnlyKeys("eleven", "twelve", "two", "six");

		assertThat(testComponent.getPartingOrOdd())
				.hasSize(6)
				.containsOnlyKeys("five", "three", "by", "hello", "goodbye", "other");
	}
}