package github.resources.img.web.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;

    private String name;

    private String password;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;

}
