package github.resources.img.web.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ImageMap {

    private Long id;

    private String imgName;

    private String relativePath;

    private String suffix;

    private Long createId;

    private Date createTime;

    /**
     * 上传IP
     */
    private String ip;

}
