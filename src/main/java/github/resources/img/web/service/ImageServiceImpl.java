package github.resources.img.web.service;

import github.resources.img.check.core.ContextHolder;
import github.resources.img.check.core.UserContext;
import github.resources.img.config.ImageFileProperties;
import github.resources.img.image.LocalBaseImage;
import github.resources.img.image.LocalBaseLocalBaseImageImpl;
import github.resources.img.image.LocalImageTransmitter;
import github.resources.img.util.ImageFileUtil;
import github.resources.img.util.ResponseUtil;
import github.resources.img.web.dao.ImgGuestMapper;
import github.resources.img.web.dao.ImgMapMapper;
import github.resources.img.web.dto.Response;
import github.resources.img.web.entity.ImageMap;
import github.resources.img.web.entity.ImgGuest;
import github.resources.img.web.exception.UserFriendlyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Component
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageFileProperties imageFileProperties;

    @Autowired
    private LocalImageTransmitter localImageTransmitter;

    @Autowired
    private ImgMapMapper imgMapMapper;

    @Autowired
    private GuestService guestService;

    @Autowired
    private ImgGuestMapper imgGuestMapper;

    @Override
    public Response saveImage(MultipartFile multipartFile) {
        if (!guestService.checkUpload()){
            return ResponseUtil.fail("游客超过一天上传的最大量");
        }
        String contentType = multipartFile.getContentType();
        LocalBaseLocalBaseImageImpl localBaseImageImpl = new LocalBaseLocalBaseImageImpl();
        if(StringUtils.hasText(contentType)&&contentType.startsWith("image/")){
            contentType = contentType.substring(contentType.indexOf("/")+1);
        }
        localBaseImageImpl.setSuffix(contentType);
        String parentPath = imageFileProperties.getLocalImageRootPath();
        String relativePath = ImageFileUtil.generateChildrenPath();
        localBaseImageImpl.setParentPath(parentPath);
        localBaseImageImpl.setRelativePath(relativePath);
        try {
            localBaseImageImpl.setInputStream(multipartFile.getInputStream());
            localBaseImageImpl.setName(UUID.randomUUID().toString().replace("-",""));
            UserContext userContext = ContextHolder.getInstance().getContext();
            localBaseImageImpl.setCreateId(userContext.getUserId());
            localBaseImageImpl.setIp(userContext.getIp());
            localImageTransmitter.writeImage(localBaseImageImpl);
            insertToDB(localBaseImageImpl);
        } catch (IOException e) {
            throw new UserFriendlyRuntimeException(e);
        }finally {
            localBaseImageImpl.close();
        }
        String imgUrl = imageFileProperties.getHost()+"/img/"+relativePath+"/"+ localBaseImageImpl.getName()+"."+ localBaseImageImpl.getSuffix();
        return ResponseUtil.ok(imgUrl);
    }

    public void insertToDB(LocalBaseImage localBaseImage){
        ImageMap imageMap = new ImageMap();
        imageMap.setImgName(localBaseImage.getName());
        imageMap.setSuffix(localBaseImage.getSuffix());
        if(StringUtils.hasText(localBaseImage.getCreateId())){
            imageMap.setCreateId(Long.parseLong(localBaseImage.getCreateId()));
        }
        imageMap.setIp(localBaseImage.getIp());
        imageMap.setRelativePath("/"+ localBaseImage.getRelativePath());
        imgMapMapper.insert(imageMap);
        UserContext context = ContextHolder.getInstance().getContext();
        if(!StringUtils.hasText(context.getUserId())){
            ImgGuest imgGuestBean = new ImgGuest();
            imgGuestBean.setGuestId(context.getIp());
            imgGuestBean.setCreateTime(new Date());
            imgGuestBean.setCount(1);
            ImgGuest imgGuest = imgGuestMapper.selectByGuestId(context.getIp());
            if(imgGuest!=null){
                imgGuestBean.setCount(imgGuest.getCount()+1);
                imgGuestMapper.updateByGuestId(imgGuestBean);
            }else {
                imgGuestMapper.insert(imgGuestBean);
            }
        }
    }
}
