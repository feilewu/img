package github.resources.img.web.dao;

import github.resources.img.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User getUserById(@Param("id") Long id);

}
