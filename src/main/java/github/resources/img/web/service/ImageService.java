package github.resources.img.web.service;

import github.resources.img.web.dto.Response;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Response saveImage(MultipartFile multipartFile);

}
