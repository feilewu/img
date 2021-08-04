package github.resources.img.web.dao;

import github.resources.img.web.entity.ImgGuest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImgGuestMapper {

    Integer insert(ImgGuest imgGuest);

    ImgGuest selectByGuestId(@Param("ip") String ip);

    Integer updateByGuestId(ImgGuest imgGuest);

    Integer deleteByGuestRecord();

}
