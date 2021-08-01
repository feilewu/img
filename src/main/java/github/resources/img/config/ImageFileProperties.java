package github.resources.img.config;

import cn.hutool.core.io.FileUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@ConfigurationProperties(
        prefix = "image.file"
)
@EnableConfigurationProperties(ImageFileProperties.class)
@Data
public class ImageFileProperties {

    private String localImageRootPath = FileUtil.getWebRoot().getParent() + File.separator + "imgFiles" + File.separator;

    private Set<String> suffixes = new HashSet<>(Arrays.asList("jpeg","png","gif","webp"));

    private String host;

}
