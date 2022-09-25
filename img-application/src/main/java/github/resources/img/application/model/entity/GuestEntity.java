package github.resources.img.application.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class GuestEntity {

    private Long id;

    private String guestId;

    private Date createTime;

    private Integer count;

}
