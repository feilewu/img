package github.resources.img.application.config;

import github.resources.img.application.properties.ImageServiceProperties;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Properties;

@Configuration
@Component
public class ImageServiceConf extends BaseConf implements InitializingBean {

    @Autowired
    private ImageServiceProperties imageServiceProperties;

    public static final ConfigOption<Boolean> ENABLE_VERIFICATION = ConfigOptions
            .key("image.service.enableVerification")
            .booleanType()
            .defaultValue(true)
            .withDescription("");

    public static final ConfigOption<String> SUPPORTED_SUFFIX = ConfigOptions
            .key("image.service.supportedSuffix")
            .stringType()
            .defaultValue("jpeg,jpg,png,gif,webp")
            .withDescription("");

    public static final ConfigOption<Boolean> DELETE_LOCAL_STORAGE_PATH_WHEN_START = ConfigOptions
            .key("image.service.deleteLocalStoragePathWhenStart")
            .booleanType()
            .defaultValue(false)
            .withDescription("");

    public static final ConfigOption<String> LOCAL_STORAGE_PATH = ConfigOptions
            .key("image.service.localPath")
            .stringType()
            .defaultValue("~/.img")
            .withDescription("");

    public static final ConfigOption<String> HOST = ConfigOptions
            .key("image.service.host")
            .stringType()
            .defaultValue("http://localhost")
            .withDescription("");


    @Override
    public void afterPropertiesSet(){
        Boolean enableVerification = imageServiceProperties.getEnableVerification();
        if (ObjectUtils.isNotEmpty(enableVerification)){
            setBoolean(ENABLE_VERIFICATION.key(),enableVerification);
        }

        String localPath = imageServiceProperties.getLocalPath();
        if(StringUtils.isBlank(localPath)){
            Properties props=System.getProperties();
            localPath = props.getProperty("user.home")+ File.separator+".img";
            setString(LOCAL_STORAGE_PATH.key(),localPath);
        }

        Boolean deleteLocalStoragePathWhenStart = imageServiceProperties.getDeleteLocalStoragePathWhenStart();
        if (ObjectUtils.isNotEmpty(deleteLocalStoragePathWhenStart)){
            setBoolean(DELETE_LOCAL_STORAGE_PATH_WHEN_START.key(),deleteLocalStoragePathWhenStart);
        }

        String host = imageServiceProperties.getHost();
        if (StringUtils.isNotEmpty(host)){
            setString(HOST.key(),host);
        }

    }

}
