package github.resources.img.web.dao;

import github.resources.img.web.entity.ImageMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImgMapMapper {

    int insert(ImageMap imageMap);

}
