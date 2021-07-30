package github.resources.img.file;

import cn.hutool.core.io.FileUtil;
import github.resources.img.config.ImageFileProperties;
import github.resources.img.util.ImageFileUtil;
import github.resources.img.web.dao.ImgMapMapper;
import github.resources.img.web.entity.ImageMap;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ImageFileProperties imageFileProperties;

    @Autowired
    private ImgMapMapper imgMapMapper;

    @Override
    public void writeImage(Image image) throws IOException {
        String parentPath = imageFileProperties.getLocalImageRootPath();
        String path = ImageFileUtil.generateChildrenPath() + File.separator + image.getName() + "." +image.getSuffix();
        File file = ImageFileUtil.createFile(parentPath, path);
        if (file == null) {
            throw new IOException();
        }
        try (InputStream inputStream = image.getInputStream();
             BufferedOutputStream outputStream = FileUtil.getOutputStream(file)) {
            byte[] buff = new byte[1024];
            for (int size; (size = inputStream.read(buff)) != -1; ) {
                outputStream.write(buff, 0, size);
            }
        } catch (Exception e) {
            throw new IOException(e);
        }
        ImageMap imageMap = new ImageMap();
        imageMap.setImgName(image.getName());
        imageMap.setSuffix(image.getSuffix());
        imageMap.setCreateId(Long.parseLong(image.getOwner()));
        imageMap.setRelativePath("/"+path);
        imgMapMapper.insert(imageMap);
    }


}
