package github.resources.img.web.service;

import github.resources.img.check.core.ContextHolder;
import github.resources.img.check.core.UserContext;
import github.resources.img.config.ImageFileProperties;
import github.resources.img.file.Image;
import github.resources.img.file.LocalBaseImage;
import github.resources.img.file.LocalImageTransmitter;
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
        LocalBaseImage localBaseImage = new LocalBaseImage();
        if(StringUtils.hasText(contentType)&&contentType.startsWith("image/")){
            contentType = contentType.substring(contentType.indexOf("/")+1);
        }
        localBaseImage.setSuffix(contentType);
        String parentPath = imageFileProperties.getLocalImageRootPath();
        String relativePath = ImageFileUtil.generateChildrenPath();
        localBaseImage.setParentPath(parentPath);
        localBaseImage.setRelativePath(relativePath);
        try {
            localBaseImage.setInputStream(multipartFile.getInputStream());
            localBaseImage.setName(UUID.randomUUID().toString().replace("-",""));
            UserContext userContext = ContextHolder.getInstance().getContext();
            localBaseImage.setCreateId(userContext.getUserId());
            localBaseImage.setIp(userContext.getIp());
            localImageTransmitter.writeImage(localBaseImage);
            insertToDB(localBaseImage);
        } catch (IOException e) {
            throw new UserFriendlyRuntimeException(e);
        }finally {
            localBaseImage.close();
        }
        String imgUrl = imageFileProperties.getHost()+"/img/"+relativePath+"/"+localBaseImage.getName()+"."+localBaseImage.getSuffix();
        return ResponseUtil.ok(imgUrl);
    }

    public void insertToDB(Image image){
        ImageMap imageMap = new ImageMap();
        imageMap.setImgName(image.getName());
        imageMap.setSuffix(image.getSuffix());
        imageMap.setId(org.apache.commons.lang3.StringUtils.isEmpty(image.getCreateId())?null:Long.parseLong(image.getCreateId()));
        imageMap.setIp(image.getIp());
        imageMap.setRelativePath("/"+image.getRelativePath());
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
