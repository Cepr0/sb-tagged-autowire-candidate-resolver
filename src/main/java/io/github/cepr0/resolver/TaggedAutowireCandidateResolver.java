package io.github.cepr0.resolver;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.ResolvableType;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TaggedAutowireCandidateResolver extends ContextAnnotationAutowireCandidateResolver {

	@Override
	public boolean isAutowireCandidate(BeanDefinitionHolder bdHolder, DependencyDescriptor descriptor) {
		if (super.isAutowireCandidate(bdHolder, descriptor)) {
			return checkTags(bdHolder, descriptor);
		}
		return false;
	}

	private boolean checkTags(BeanDefinitionHolder bdHolder, DependencyDescriptor descriptor) {
		Tags targetAnn = descriptor.getAnnotation(Tags.class);
		if (targetAnn != null) {
			BeanDefinition bd = bdHolder.getBeanDefinition();
			Object source = bd.getSource();

			String[] candidateTags = null;

			if (source instanceof AnnotatedTypeMetadata) { // source is not null when a candidate was created with @Bean annotation
				Map<String, Object> attributes = ((AnnotatedTypeMetadata) source).getAnnotationAttributes(Tags.class.getName());
				if (attributes != null) {
					candidateTags = (String[]) attributes.get("value");
				}
			} else {
				ResolvableType candidateType = ((RootBeanDefinition) bd).getResolvableType();
				if (candidateType != null) { // candidateType is not null when candidate was created with @Component like annotation
					Class<?> candidateClass = candidateType.resolve();
					if (candidateClass != null) {
						Tags tagsAnn = candidateClass.getAnnotation(Tags.class);
						if (tagsAnn != null) {
							candidateTags = tagsAnn.value();
						}
					}
				}
			}

			if (candidateTags != null) {
				List<String> targetTags = new ArrayList<>(Arrays.asList(targetAnn.value()));
				targetTags.retainAll(Arrays.asList(candidateTags));
				return !targetTags.isEmpty();
			} else {
				// If a candidate doesn't have @Tags annotation then it's not a suitable candidate
				return false;
			}
		}
		// If target doesn't have @Tags annotation then return 'true' as super.isAutowireCandidate() does.
		return true;
	}
}
