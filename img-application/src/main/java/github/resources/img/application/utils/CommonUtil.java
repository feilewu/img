package github.resources.img.application.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;

import java.util.Date;

import static cn.hutool.core.date.DatePattern.PURE_DATE_PATTERN;

public class CommonUtil {

    /**
     * yyyy/MM/dd linux
     * @return
     */
    public static String generatePrefixPath(){
        Date date = DateUtil.date();
        String prefix = DateUtil.format(date, "yyyy/MM/dd");
        if (FileUtil.isWindows()){
            prefix = prefix.replace("/","\\");
        }
        return prefix;
    }

    public static String generateDate(){
        Date date = DateUtil.date();
        return DateUtil.format(date,PURE_DATE_PATTERN);
    }

}
