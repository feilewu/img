package github.resources.img.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import github.resources.img.core.configuration.ImageServiceConf;
import github.resources.img.core.model.bo.ImageBo;
import github.resources.img.storage.*;
import github.resources.img.storage.dao.ImageMetaMapper;
import github.resources.img.storage.dao.LocalImageMapper;
import github.resources.img.storage.entity.ImageMetaEntity;
import github.resources.img.storage.entity.LocalImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;


/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 0:51
 **/
@Component("localImageManager")
public class LocalImageManager implements ImageManager, Storage {

    @Autowired
    private ImageMetaMapper imageMetaMapper;

    @Autowired
    private LocalImageMapper localImageMapper;

    @Autowired
    private ImageServiceConf imageServiceConf;

    private final Storage storage;

    public LocalImageManager() {
        this.storage = StorageFactory.getInstance().getStorage(StorageType.LOCAL);
    }


    @Override
    public void write(Image image) {
        storage.write(image);
    }

    @Override
    public Image read(Image image) {
        return storage.read(image);
    }

    @Override
    public void save(ImageBo imageBo) {
        final ImageMetaEntity imageMetaEntity = createImageMetaEntity(imageBo);
        imageMetaMapper.insert(imageMetaEntity);

        final LocalImageEntity localImageEntity = createLocalImageEntity(imageBo);
        localImageMapper.insert(localImageEntity);

        final LocalImage localImage = createLocalImage(imageBo);
        storage.write(localImage);

    }

    @Override
    public ImageBo get(String fileName) {
        QueryWrapper<LocalImageEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("img_name", fileName.substring(0, fileName.lastIndexOf(".")));
        final LocalImageEntity localImageEntity = localImageMapper.selectOne(queryWrapper);
        LocalImage localImage = new LocalImage();
        String localPath = imageServiceConf.getString(ImageServiceConf.LOCAL_STORAGE_PATH);
        String prefix = localImageEntity.getPrefixPath();
        if (prefix.startsWith("/")){
            prefix = prefix.replaceFirst("/","");
        }
        String uri = localPath
                + File.separator + prefix + File.separator+ fileName;
        localImage.setUri(uri);
        final LocalImage result = (LocalImage) storage.read(localImage);
        ImageBo imageBo = new ImageBo();
        imageBo.setContent(result.getContent());
        imageBo.setName(localImageEntity.getImgName());
        imageBo.setSuffix(localImageEntity.getSuffix());
        return imageBo;
    }

    private LocalImageEntity createLocalImageEntity(ImageBo imageBo){
        LocalImageEntity localImageEntity = new LocalImageEntity();
        localImageEntity.setImgName(imageBo.getName());
        localImageEntity.setSuffix(imageBo.getSuffix());
        localImageEntity.setPrefixPath(imageBo.getPrefix());
        return localImageEntity;
    }

    private ImageMetaEntity createImageMetaEntity(ImageBo imageBo) {
        ImageMetaEntity imageMetaEntity = new ImageMetaEntity();
        imageMetaEntity.setCreateId(imageBo.getOwner());
        imageMetaEntity.setImgName(imageBo.getName());
        imageMetaEntity.setSuffix(imageBo.getSuffix());
        imageMetaEntity.setCreateTime(new Date());
        return imageMetaEntity;
    }

    private LocalImage createLocalImage(ImageBo imageBo){
        LocalImage localImage = new LocalImage();
        localImage.setContent(imageBo.getContent());
        localImage.setUri(imageBo.getUri());
        return localImage;
    }




}
