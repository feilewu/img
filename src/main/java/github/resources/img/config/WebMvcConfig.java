package github.resources.img.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ImageFileProperties imageFileProperties;

//    @Autowired
//    private AuthUploadInterceptor authUploadInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authUploadInterceptor)
//                .addPathPatterns("/**");
//    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:"+imageFileProperties.getLocalImageRootPath());
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:static/");
    }

}
