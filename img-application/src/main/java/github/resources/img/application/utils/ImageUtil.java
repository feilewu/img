package github.resources.img.application.utils;

import github.resources.img.application.configuration.DefaultImageServiceConf;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import static github.resources.img.application.configuration.DefaultImageServiceConf.SUPPORTED_SUFFIX;

public class ImageUtil {

    private ImageUtil(){

    }

    public static boolean supportedImageType(MultipartFile file, DefaultImageServiceConf conf){
        String contentType = file.getContentType();
        if(StringUtils.hasText(contentType)&&contentType.startsWith("image/")){
            String suffix = contentType.substring(contentType.indexOf("/")+1);
            final String[] supportedSuffixs = conf.getString(SUPPORTED_SUFFIX.key(), SUPPORTED_SUFFIX.defaultValue())
                    .split(",");

            for (String item:supportedSuffixs){
                if(org.apache.commons.lang3.StringUtils.equals(suffix,item)){
                    return true;
                }


            }

        }
        return false;


    }


}
