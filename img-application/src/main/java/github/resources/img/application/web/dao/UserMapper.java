package github.resources.img.application.web.dao;

import github.resources.img.application.model.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    UserEntity getUserById(@Param("id") Long id);

    UserEntity getUserByName(@Param("name") String name);


    int insertUser(UserEntity userEntity);


}
