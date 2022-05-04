package github.resources.img.manager.dao;

import github.resources.img.manager.entity.ImageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImgMapper {

    int insert(ImageEntity image);

    ImageEntity getByName(@Param("imgName") String name);

}
