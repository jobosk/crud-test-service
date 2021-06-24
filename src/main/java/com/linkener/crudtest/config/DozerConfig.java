package com.linkener.crudtest.config;

import com.belike.core.mapper.dozer.converters.EntityListDozerConverter;
import com.github.dozermapper.core.CustomConverter;
import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DozerConfig {

	@Bean
	public DozerBeanMapperFactoryBean dozerMapper(final EntityListDozerConverter entityListDozerConverter) throws IOException {
		DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:dozerBeanMapping.xml");
		dozerBeanMapperFactoryBean.setMappingFiles(resources);
		Map<String, CustomConverter> customConvertersWithId = new HashMap<>();
		customConvertersWithId.put("entityListDozerConverter", entityListDozerConverter);
		dozerBeanMapperFactoryBean.setCustomConvertersWithId(customConvertersWithId);
		return dozerBeanMapperFactoryBean;
	}
}