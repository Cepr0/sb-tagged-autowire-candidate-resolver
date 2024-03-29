# TaggedAutowireCandidateResolver 
_An example of custom implementation of Spring [AutowireCandidateResolver][1]_

[TaggedAutowireCandidateResolver][2] is an implementation of `AutowireCandidateResolver` 
that makes it possible to get collections of injected Spring beans 
tagged by specified 'tags'. Tags are specified with [@Tags][3] annotation 
in style of `@Qualifier` annotation. Collection is formed from the beans 
that have the same type and the same tags as the autowired collection.

```java
@Autowired
@Tags({"greeting", "other"})
private Map<String, Supplier<String>> greetingOrOther;

@Configuration
static class Beans {
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
}
``` 
Here Map `greetingOrOther` will have three `Supplier<String>` beans: `hi`, `hello` and `other`;

To make it work you have to register a [CustomAutowireConfigurer][6] bean in your application
and provide it with `TaggedAutowireCandidateResolver`:

```java
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
```

More usage examples are in [Test][4]

This project is related to this [SO post][5].

[1]: https://bit.ly/2Yj8qK0
[2]: src/main/java/io/github/cepr0/resolver/TaggedAutowireCandidateResolver.java
[3]: src/main/java/io/github/cepr0/resolver/Tags.java
[4]: src/test/java/io/github/cepr0/resolver/TaggedAutowireCandidateResolverTest.java
[5]: https://stackoverflow.com/a/57169506
[6]: https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-custom-autowire-configurer 