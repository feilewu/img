package github.resources.img.application.dao;

import github.resources.img.application.entity.GuestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImgGuestMapper {

    Integer insert(GuestEntity guestEntity);

    GuestEntity selectByGuestId(@Param("ip") String ip);

    Integer updateByGuestId(GuestEntity guestEntity);

    Integer deleteGuestRecord();

}
