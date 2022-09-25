package github.resources.img.application.service.impl;

import github.resources.img.application.configuration.ImageServiceHolder;
import github.resources.img.application.model.dto.Response;
import github.resources.img.application.service.ImgService;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.manager.ImageManager;
import github.resources.img.manager.StorageManger;
import github.resources.img.manager.bo.ImageBo;
import github.resources.img.manager.dao.ImgMapper;
import github.resources.img.manager.entity.ImageEntity;
import github.resources.img.manager.exception.ReadRuntimeException;
import github.resources.img.manager.storage.StorageType;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

import static github.resources.img.application.configuration.ImageServiceConf.HOST;
import static github.resources.img.application.configuration.ImageServiceConf.LOCAL_STORAGE_PATH;

@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private StorageManger storageManger;

    @Autowired
    private ImgMapper imgMapper;

    @Resource(name = "localImageManager")
    private ImageManager imageManager;

    @Override
    public Response upload(ImageBo imageBo) {
        storageManger.write(imageBo);
        String host = ImageServiceHolder.getImageServiceConf().getString(HOST);
        return ResponseUtil.ok(host+"/img/"+imageBo.getName()+"."+imageBo.getSuffix());
    }

    @Override
    public ImageBo readImg(String fileName) {
        return readRawImg(fileName);
    }

    private ImageBo readRawImg(String fileName){
        final ImageEntity imageEntity = imgMapper.getByName(fileName.substring(0, fileName.lastIndexOf(".")));
        if (ObjectUtils.isEmpty(imageEntity)){
            throw new ReadRuntimeException("can not find img in database, uri: "+fileName);
        }
        String localPath = ImageServiceHolder.getImageServiceConf().getString(LOCAL_STORAGE_PATH);
        String prefix = imageEntity.getPrefixPath();
        if (prefix.startsWith("/")){
            prefix = prefix.replaceFirst("/","");
        }
        String uri = localPath
                + File.separator + prefix + File.separator+fileName;
        final ImageBo imageBo = storageManger.read(uri, StorageType.LOCAL);
        if (ObjectUtils.isEmpty(imageEntity)){
            throw new ReadRuntimeException("can not find img in disk, uri: "+fileName);
        }
        imageBo.setLocalPath(localPath);
        imageBo.setSuffix(imageEntity.getSuffix());
        return imageBo;
    }

}
