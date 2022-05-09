package github.resources.img.application.appconfig;

import github.resources.img.application.filters.ApiImgUploadFilter;
import github.resources.img.application.filters.SiteImgUploadFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean<ApiImgUploadFilter> registerApiImgUploadFilter(){
        FilterRegistrationBean<ApiImgUploadFilter> bean = new FilterRegistrationBean<>();
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        bean.setFilter(new ApiImgUploadFilter());
        bean.addUrlPatterns("/api/img/upload");
        return bean;
    }

    @Bean
    public FilterRegistrationBean<SiteImgUploadFilter> registerSiteImgUploadFilter(){
        FilterRegistrationBean<SiteImgUploadFilter> bean = new FilterRegistrationBean<>();
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        bean.setFilter(new SiteImgUploadFilter());
        bean.addUrlPatterns("/site/img/upload");
        return bean;
    }

}
