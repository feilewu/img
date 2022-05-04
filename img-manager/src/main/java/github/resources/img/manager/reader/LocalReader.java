package github.resources.img.manager.reader;

import github.resources.img.manager.ImageReader;
import github.resources.img.manager.bo.ImageBo;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class LocalReader implements ImageReader {

    @Override
    public ImageBo read(String uri) {
        try (InputStream inputStream = new FileInputStream(uri);
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()){
            int nRead;
            byte[] data = new byte[128];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            byte[] content = buffer.toByteArray();
            ImageBo imageBo = new ImageBo();
            imageBo.setUri(uri);
            imageBo.setContent(content);
            return imageBo;
        } catch (IOException e) {
            log.error("can not read file: {},error: ",uri,e);
        }
        return null;

    }
}
