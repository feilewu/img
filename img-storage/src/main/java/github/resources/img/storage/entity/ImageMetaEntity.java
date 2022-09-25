package github.resources.img.storage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import github.resources.img.storage.Image;
import lombok.Data;

import java.util.Date;

@TableName("img_meta")
@Data
public class ImageMetaEntity implements Image {

    private Long id;

    private String imgName;

    /**
     * 默认扩展名
     */
    private String suffix;

    private Long createId;

    private Date createTime;

    /**
     * 上传IP
     */
    private String ip;

}
