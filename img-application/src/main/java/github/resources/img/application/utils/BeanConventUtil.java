package github.resources.img.application.utils;

import github.resources.img.application.config.ImageServiceHolder;
import github.resources.img.manager.bo.ImageBo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static github.resources.img.application.config.ImageServiceConf.LOCAL_STORAGE_PATH;

public class BeanConventUtil {

    private BeanConventUtil() {

    }

    public static ImageBo toBo(MultipartFile file){
        try (InputStream inputStream = file.getInputStream();
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()){
            int nRead;
            byte[] data = new byte[128];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            byte[] content = buffer.toByteArray();
            ImageBo imageBo = new ImageBo();
            imageBo.setContent(content);
            String fileName = UUID.randomUUID().toString().replace("-","");
            imageBo.setName(fileName);
            String contentType = file.getContentType();
            String suffix = null;
            if(StringUtils.isNotBlank(contentType)&&contentType.startsWith("image/")){
                suffix = contentType.substring(contentType.indexOf("/")+1);
            }
            if (StringUtils.isBlank(suffix)){
                throw new IllegalArgumentException("error image suffix");
            }
            imageBo.setSuffix(suffix);
            String localPath = ImageServiceHolder.getImageServiceConf().getString(LOCAL_STORAGE_PATH);
            imageBo.setLocalPath(localPath);
            String prefix = CommonUtil.generatePrefixPath();
            imageBo.setUri(localPath+File.separator+prefix+File.separator+fileName+"."+suffix);
            return imageBo;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }


}
