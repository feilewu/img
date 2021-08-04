package github.resources.img.web.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ImgGuest {

    private Long id;

    private String guestId;

    private Date createTime;

    private Integer count;

}
