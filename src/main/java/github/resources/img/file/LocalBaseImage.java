package github.resources.img.file;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class LocalBaseImage implements Image{

    private String parentPath;

    private String relativePath;

    private String name;

    private String owner;

    private String suffix;

    private InputStream inputStream;

    @Override
    public String getParentPath() {
        return parentPath;
    }

    @Override
    public String getRelativePath() {
        return relativePath;
    }

    @Override
    public String getOwner() {
        return owner;
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
