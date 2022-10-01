package github.resources.img.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("img_user")
public class UserEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String password;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;

}
