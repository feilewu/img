package github.resources.img.core.model.dto.request;

import lombok.Data;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/10/7 16:40
 **/
@Data
public class QueryPageRequest {

    private Long current;

    private Long size;

}
