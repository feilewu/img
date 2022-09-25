package github.resources.img.storage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 0:39
 **/
@Slf4j
public class LocalStorage implements Storage{


    @Override
    public void write(Image image) {
        if (image instanceof LocalImage){
            LocalImage localImage = (LocalImage) image;
            String uri = localImage.getUri();
            byte[] content = localImage.getContent();
            if (StringUtils.isBlank(uri) || ObjectUtils.isEmpty(content)){
                throw new IllegalArgumentException("File name or content is empty");
            }
            if(FileUtil.exist(uri)){
                FileUtil.del(uri);
            }
            FileUtil.touch(uri);
            try (FileOutputStream outputStream = new FileOutputStream(uri)){
                outputStream.write(content,0,content.length);
            } catch (Exception e) {
                throw new IORuntimeException("failed to write image bytes into disk",e);
            }

        }else {
            throw new IllegalArgumentException("image type is error, must be LocalImage");
        }
    }

    @Override
    public Image read(Image image) {
        if (image instanceof LocalImage){
            return read(((LocalImage) image).getUri());
        }else {
            throw new IllegalArgumentException("image type is error, must be LocalImage");
        }
    }

    private LocalImage read(String uri){
        try (InputStream inputStream = new FileInputStream(uri);
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()){
            int nRead;
            byte[] data = new byte[128];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            byte[] content = buffer.toByteArray();
            LocalImage localImage = new LocalImage();
            localImage.setUri(uri);
            localImage.setContent(content);
            return localImage;
        } catch (IOException e) {
            log.error("can not read file: {},error: ",uri,e);
        }
        return null;
    }
}
