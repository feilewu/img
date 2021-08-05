package github.resources.img.web.service;

import github.resources.img.check.core.ContextHolder;
import github.resources.img.check.core.UserContext;
import github.resources.img.web.dao.ImgGuestMapper;
import github.resources.img.web.entity.ImgGuest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService{

    @Autowired
    private ImgGuestMapper imgGuestMapper;

    /**
     * 是否通过
     * @return true 通过，false 拦截
     */
    @Override
    public boolean checkUpload() {
        UserContext context = ContextHolder.getInstance().getContext();
        if(StringUtils.isEmpty(context.getUserId())){
            ImgGuest imgGuest = imgGuestMapper.selectByGuestId(context.getIp());
            return imgGuest==null || imgGuest.getCount() < 10;
        }
        return true;
    }
}
