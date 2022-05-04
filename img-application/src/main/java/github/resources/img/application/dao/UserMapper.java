package github.resources.img.application.dao;

import github.resources.img.application.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    UserEntity getUserById(@Param("id") Long id);

}
