package github.resources.img.storage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import github.resources.img.storage.Image;
import lombok.Data;

@TableName("img_local_storage_info")
@Data
public class LocalImageEntity implements Image {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String imgName;

    private String prefixPath;

    private String suffix;


}
