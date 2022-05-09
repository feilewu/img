package github.resources.img.application.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(
        prefix = "image.service"
)
@EnableConfigurationProperties(ImageServiceProperties.class)
@Data
public class ImageServiceProperties {

    private String supportedSuffix;

    private Boolean enableVerification;

    private String localPath;

    private Boolean deleteLocalStoragePathWhenStart;

    private String host;

}
