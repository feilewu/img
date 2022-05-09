package github.resources.img.application.web;

import github.resources.img.application.config.ImageServiceHolder;
import github.resources.img.application.service.ImgService;
import github.resources.img.application.utils.ResponseUtil;
import github.resources.img.application.utils.WebUtil;
import github.resources.img.manager.bo.ImageBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static github.resources.img.application.config.ImageServiceConf.SUPPORTED_SUFFIX;

@Controller
@RequestMapping("/img")
public class ImgController {

    @Autowired
    private ImgService imgService;

    @GetMapping("/{fileName}")
    public void file(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response){
        final ImageBo imageBo = imgService.readImg(fileName);
        reply(imageBo,response);
    }


    @GetMapping("/**")
    public void getImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String uri = request.getRequestURI();
        final List<String> suffixs = Arrays.asList(ImageServiceHolder.getImageServiceConf().getString(SUPPORTED_SUFFIX).split(","));
        String suffix = uri.substring(uri.lastIndexOf(".")+1);
        if (!suffixs.contains(suffix)){
            WebUtil.replyJson(response, ResponseUtil.fail("unsupported uri, the suffix "+suffix+" is not supported"));
        }
        String fileName = uri.substring(uri.lastIndexOf("/")+1);
        final ImageBo imageBo = imgService.readImg(fileName);
        reply(imageBo,response);
    }

    private void reply(ImageBo imageBo, HttpServletResponse response){
        response.setContentType("image/"+imageBo.getSuffix());
        try (final ServletOutputStream outputStream = response.getOutputStream()){
            outputStream.write(imageBo.getContent());
            outputStream.flush();
        } catch (IOException e) {

        }
    }

}
