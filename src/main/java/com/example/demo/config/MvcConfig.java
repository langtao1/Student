package com.example.demo.config;

import com.example.demo.dict.BaseEnumConvertFactory;
import com.example.demo.dict.EnumWebSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Description:
 * @Author: eagle
 * @Date: 2020-07-11 23:23
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    BaseEnumConvertFactory baseEnumConvertFactory;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.stream().filter(converter->converter instanceof MappingJackson2HttpMessageConverter).forEach(converter->{
            MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter)converter;
            EnumWebSerializer enumWebSerializer = new EnumWebSerializer();
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(enumWebSerializer);
            jsonConverter.getObjectMapper().registerModule(simpleModule);
        });
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(baseEnumConvertFactory);
    }

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api-docs", "/swagger-ui.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**")
                .addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}