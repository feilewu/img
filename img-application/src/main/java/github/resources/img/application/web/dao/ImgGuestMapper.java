package github.resources.img.application.web.dao;

import github.resources.img.application.model.entity.GuestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImgGuestMapper {

    Integer insert(GuestEntity guestEntity);

    GuestEntity selectByGuestId(@Param("ip") String ip);

    Integer updateByGuestId(GuestEntity guestEntity);

    Integer deleteGuestRecord();

}
