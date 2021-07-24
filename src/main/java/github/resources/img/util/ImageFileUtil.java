package github.resources.img.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.Date;

public class ImageFileUtil {

    private ImageFileUtil(){

    }

    public static String generateChildrenPath(){
        Date date = DateUtil.date();
        return DateUtil.format(date, "yyyy/MM/dd");
    }

    public static File mkdir(String path){
        return FileUtil.mkdir(path);
    }

    public static File createFile(String parentPath,String path){
        String fullPath = parentPath+File.separator+path;
        if(FileUtil.exist(fullPath)){
            FileUtil.del(fullPath);
        }
        return FileUtil.touch(fullPath);
    }


}
