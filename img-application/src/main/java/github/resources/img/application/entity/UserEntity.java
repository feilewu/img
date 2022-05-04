package github.resources.img.application.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {

    private Long id;

    private String name;

    private String password;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;

}
