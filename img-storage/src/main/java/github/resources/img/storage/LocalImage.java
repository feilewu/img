package github.resources.img.storage;

import github.resources.img.storage.entity.LocalImageEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/25 17:52
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class LocalImage extends LocalImageEntity {

    private byte[] content;

    private String uri;

}
