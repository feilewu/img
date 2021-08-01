package github.resources.img.web.service;

import github.resources.img.check.core.ContextHolder;
import github.resources.img.check.core.UserContext;
import github.resources.img.file.LocalBaseImage;
import github.resources.img.file.LocalImageTransmitter;
import github.resources.img.util.ResponseUtil;
import github.resources.img.web.controller.site.UserController;
import github.resources.img.web.dto.Response;
import github.resources.img.web.exception.UserFriendlyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class ImageServiceImpl implements ImageService{

    @Autowired
    private LocalImageTransmitter localImageTransmitter;

    @Override
    public Response saveImage(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        LocalBaseImage localBaseImage = new LocalBaseImage();
        if(StringUtils.hasText(contentType)&&contentType.startsWith("image/")){
            contentType = contentType.substring(contentType.indexOf("/")+1);
        }
        localBaseImage.setSuffix(contentType);
        try {
            localBaseImage.setInputStream(multipartFile.getInputStream());
            localBaseImage.setName(UUID.randomUUID().toString().replace("-",""));
            UserContext userContext = ContextHolder.getInstance().getContext();
            localBaseImage.setOwner(userContext.getOwner());
            localImageTransmitter.writeImage(localBaseImage);
        } catch (IOException e) {
            throw new UserFriendlyRuntimeException(e);
        }finally {
            localBaseImage.close();
        }
        return ResponseUtil.ok();
    }
}
