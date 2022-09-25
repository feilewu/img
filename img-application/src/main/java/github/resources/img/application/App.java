package github.resources.img.application;

import cn.hutool.core.io.FileUtil;
import github.resources.img.application.configuration.DefaultImageServiceConf;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static github.resources.img.application.configuration.DefaultImageServiceConf.DELETE_LOCAL_STORAGE_PATH_WHEN_START;
import static github.resources.img.application.configuration.DefaultImageServiceConf.LOCAL_STORAGE_PATH;

@SpringBootApplication(scanBasePackages = {"github.resources.img"})
@MapperScan(basePackages = {"github.resources.img.application.web.dao","github.resources.img.*.dao"})
@EnableScheduling
@EnableWebMvc
@Slf4j
public class App implements ApplicationRunner {

    @Autowired
    private DefaultImageServiceConf conf;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        final String path = conf.getString(LOCAL_STORAGE_PATH);
        if (conf.getBoolean(DELETE_LOCAL_STORAGE_PATH_WHEN_START)){
            FileUtil.del(path);
            FileUtil.mkdir(path);
            log.info("delete old image data folder: {}", path);
            log.info("create new image data folder: {}", path);
        }else {
            if (FileUtil.exist(path)){
                log.warn("path: {} exist, use the existed path",path);
            }else {
                FileUtil.mkdir(path);
                log.info("create new image data folder: {}", path);
            }
        }
    }


}
