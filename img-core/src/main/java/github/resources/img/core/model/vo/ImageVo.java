package github.resources.img.core.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/10/7 15:00
 **/
@Data
public class ImageVo {

    private String owner;

    private String uri;

    private Date createTime;

}
