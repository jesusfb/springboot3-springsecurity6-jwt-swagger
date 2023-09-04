package com.sample.server.auth.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import java.util.List;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    static class IndexFallbackResourceResolver extends PathResourceResolver {
        @Override
        protected Resource resolveResourceInternal(HttpServletRequest request, String requestPath,
                                                   List<? extends Resource> locations, ResourceResolverChain chain) {
            Resource resource = super.resolveResourceInternal(request, requestPath, locations, chain);
            if(resource==null){
                //try with /index.html
                resource = super.resolveResourceInternal(request, requestPath + "/index.html", locations, chain);
            }
            return resource;
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // src/main/resources/templates/static/...
        registry
                .addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/swagger-ui/");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/swagger-ui/", "/swagger-ui/index.html");
    }
}
