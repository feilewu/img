package github.resources.img.manager.entity;

import lombok.Data;

import java.util.Date;

/**
 * 2011/08/21/people.png
 * prefixPath --- 2011/08/21
 * imgName --- people
 * suffix --- png
 */
@Data
public class ImageEntity {

    private Long id;

    private String imgName;

    private String prefixPath;

    private String suffix;

    private Long createId;

    private Date createTime;

    /**
     * 上传IP
     */
    private String ip;




}
