package github.resources.img.application.beans;

import github.resources.img.core.configuration.ImageServiceConf;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 20:56
 **/
@Component
public class ServiceConfig {

    @Bean
    public ImageServiceConf imageServiceConf(){
        return new ImageServiceConf();
    }


}
