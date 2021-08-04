package github.resources.img.file;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class LocalBaseImage implements Image{

    private String parentPath;

    private String relativePath;

    /**
     * 图片名字
     */
    private String name;

    private String createId;

    private String suffix;

    private String ip;

    private InputStream inputStream;

    @Override
    public String getParentPath() {
        return parentPath;
    }

    @Override
    public String getRelativePath() {
        return relativePath;
    }

    public String getCreateId() {
        return createId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public void close() {
        if(inputStream!=null){
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
