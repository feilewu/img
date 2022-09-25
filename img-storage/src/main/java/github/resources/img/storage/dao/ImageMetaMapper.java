package github.resources.img.storage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.resources.img.storage.entity.ImageMetaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 0:20
 **/
@Mapper
public interface ImageMetaMapper extends BaseMapper<ImageMetaEntity> {
}
