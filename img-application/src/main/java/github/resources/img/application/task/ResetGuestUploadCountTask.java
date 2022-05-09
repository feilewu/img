package github.resources.img.application.task;

import github.resources.img.application.dao.ImgGuestMapper;
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
        Integer record = imgGuestMapper.deleteGuestRecord();
        if(record!=null&&record>=0){
            log.info("重置img_guest表成功");
        }else {
            log.info("重置img_guest表失败");
        }
    }

}
