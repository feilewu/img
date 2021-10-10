package github.resources.img.image;

import cn.hutool.core.io.FileUtil;
import github.resources.img.util.ImageFileUtil;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 保存图片到本地
 */
@Component
public class LocalImageTransmitter implements ImageTransmitter{

    @Override
    public void writeImage(LocalBaseImage localBaseImage) throws IOException {
        String path = localBaseImage.getRelativePath() + File.separator + localBaseImage.getName() + "." + localBaseImage.getSuffix();
        File file = ImageFileUtil.createFile(localBaseImage.getParentPath(), path);
        if (file == null) {
            throw new IOException();
        }
        try (InputStream inputStream = localBaseImage.getInputStream();
             BufferedOutputStream outputStream = FileUtil.getOutputStream(file)) {
            byte[] buff = new byte[1024];
            for (int size; (size = inputStream.read(buff)) != -1; ) {
                outputStream.write(buff, 0, size);
            }
        } catch (Exception e) {
            throw new IOException(e);
        }
    }




}
