package github.resources.img.task;

import github.resources.img.web.dao.ImgGuestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResetGuestUploadCountTask {

    @Autowired
    private ImgGuestMapper imgGuestMapper;

    @Scheduled(cron = "0 0 0 * * ?")
    public void doReset(){
        Integer record = imgGuestMapper.deleteByGuestRecord();
        if(record!=null&&record>=0){
            log.info("重置img_guest表成功");
        }else {
            log.info("重置img_guest表失败");
        }
    }

}
