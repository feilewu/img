package github.resources.img.storage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.resources.img.storage.entity.LocalImageEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 0:24
 **/
@Mapper
public interface LocalImageMapper extends BaseMapper<LocalImageEntity> {
}
