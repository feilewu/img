package github.resources.img.storage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import github.resources.img.storage.Image;
import lombok.Data;

@TableName("img_local_storage_info")
@Data
public class LocalImageEntity implements Image {

    private Long id;

    private String imgName;

    private String prefixPath;

    private String suffix;


}
