package github.resources.img.application.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.resources.img.application.model.entity.GuestEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/29 22:46
 **/
@Mapper
public interface ImgGuestMapper extends BaseMapper<GuestEntity> {
}
