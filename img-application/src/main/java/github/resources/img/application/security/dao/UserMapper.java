package github.resources.img.application.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.resources.img.core.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/29 22:35
 **/
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
