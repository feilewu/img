package github.resources.img.manager.writer;

import cn.hutool.core.io.FileUtil;
import github.resources.img.manager.bo.ImageBo;
import github.resources.img.manager.ImageWriter;
import github.resources.img.manager.dao.ImgMapper;
import github.resources.img.manager.entity.ImageEntity;
import github.resources.img.manager.exception.WriteRunTimeException;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Date;

@Data
public class LocalWriter implements ImageWriter {

    private ImgMapper imgMapper;

    @Override
    public void write(ImageBo imageBo) {
        String uri = imageBo.getUri();
        String localPath = imageBo.getLocalPath();
        byte[] content = imageBo.getContent();
        if (StringUtils.isBlank(uri) || ObjectUtils.isEmpty(content)){
            throw new WriteRunTimeException("File name or content is empty");
        }
        if(FileUtil.exist(uri)){
            FileUtil.del(uri);
        }
        FileUtil.touch(uri);

        try (FileOutputStream outputStream = new FileOutputStream(uri)){
            outputStream.write(imageBo.getContent(),0,content.length);
        } catch (Exception e) {
            throw new WriteRunTimeException(e);
        }
        String suffix = getSuffix(uri);
        String prefix = getPrefix(uri, localPath);
        String imageName = getImageName(uri);
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setCreateId(imageBo.getOwner());
        imageEntity.setImgName(imageName);
        imageEntity.setSuffix(suffix);
        imageEntity.setPrefixPath(prefix);
        imageEntity.setCreateTime(new Date());
        if (imgMapper.insert(imageEntity)<1){
            FileUtil.del(uri);
            throw new WriteRunTimeException("failed to insert info into database");
        }
    }

    private String getImageName(String uri){
        return uri.substring(uri.lastIndexOf(File.separator)+1,uri.lastIndexOf("."));
    }


    private String getSuffix(String uri){
        return uri.substring(uri.lastIndexOf(".")+1);

    }

    private String getPrefix(String uri, String localPath){
        String prefix = uri.substring(0,uri.lastIndexOf(File.separator)).replace(localPath+File.separator,"");
        if (FileUtil.isWindows()){
            prefix = prefix.replace(File.separator,"/");
        }
        return prefix;
    }

}
