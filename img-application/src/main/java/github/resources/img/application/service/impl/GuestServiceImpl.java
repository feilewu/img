package github.resources.img.application.service.impl;

import github.resources.img.application.web.dao.ImgGuestMapper;
import github.resources.img.application.model.entity.GuestEntity;
import github.resources.img.application.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private ImgGuestMapper imgGuestMapper;

    /**
     * 是否通过
     * @return true 通过，false 拦截
     */
    @Override
    public boolean checkUpload(String ip) {
        GuestEntity imgGuest = imgGuestMapper.selectByGuestId(ip);
        return imgGuest==null || imgGuest.getCount() < 10;

    }

    @Override
    public void updateGuestUpload(String ip) {
        final GuestEntity guest = imgGuestMapper.selectByGuestId(ip);
        if(guest!=null){
            guest.setCount(guest.getCount()+1);
            imgGuestMapper.updateByGuestId(guest);
        }else {
            GuestEntity guestEntity = new GuestEntity();
            guestEntity.setCount(1);
            guestEntity.setGuestId(ip);
            guestEntity.setCreateTime(new Date());
            imgGuestMapper.insert(guestEntity);
        }
    }



}
