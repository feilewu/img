package github.resources.img.application.filters;

import github.resources.img.application.config.ContextHolder;
import github.resources.img.application.config.ImageServiceHolder;
import github.resources.img.application.config.UserContext;
import github.resources.img.application.service.GuestService;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.application.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SiteImgUploadFilter extends AbstractImgUploadFilter {

    @Override
    public boolean doCustomAuth(HttpServletRequest request, HttpServletResponse response){
        final String ipAddress = WebUtil.getIpAddress(request);
        final GuestService guestService = ImageServiceHolder.getContext().getBean(GuestService.class);
        if(guestService.checkUpload(ipAddress)){
            final UserContext context = ContextHolder.getInstance().getContext();
            context.setGuest(true);
            context.setIp(ipAddress);
            return true;
        }else {
            WebUtil.replyJson(response, ResponseUtil.fail("Tourists can only upload 10 pictures a day"));
            return false;
        }
    }
}
