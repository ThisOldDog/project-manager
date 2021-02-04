package pers.dog.project.manager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import pers.dog.project.manager.component.argument.PageHandlerMethodArgumentResolver;

import java.util.List;

/**
 * Web Mvc 配置
 *
 * @author 废柴 2021/2/3 20:22
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageHandlerMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }
}
