package github.resources.img.application.configuration;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ImageServiceHolder implements ApplicationContextAware {

    @Getter
    private static ApplicationContext context;

    private static ImageServiceConf conf;

    public static ImageServiceConf getImageServiceConf() {
        return conf;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        final ImageServiceConf imageServiceConf = applicationContext.getBean(ImageServiceConf.class);
        if (ObjectUtils.isEmpty(imageServiceConf)){
            throw new IllegalStateException("can not get bean of ImageServiceConf");
        }
        conf = imageServiceConf;
//        final SecurityManager securityManager = applicationContext.getBean(SecurityManager.class);
//        if (ObjectUtils.isEmpty(securityManager)){
//            throw new IllegalStateException("can not get bean of SecurityManager");
//        }
//        manager = securityManager;
    }
}
