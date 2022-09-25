package github.resources.img.core.configuration;

import java.io.File;
import java.util.Properties;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 20:53
 **/
public class ImageServiceConf extends BaseConf {

    private static final Properties props=System.getProperties();

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
            .defaultValue(props.getProperty("user.home")+ File.separator+".img")
            .withDescription("");

    public static final ConfigOption<String> HOST = ConfigOptions
            .key("image.service.host")
            .stringType()
            .defaultValue("http://localhost")
            .withDescription("");

}
