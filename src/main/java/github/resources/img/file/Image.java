package github.resources.img.file;

import java.io.InputStream;

public interface Image {


    String getOwner();
    /**
     * file name
     * @return
     */
    String getName();

    String getSuffix();

    /**
     * 保证每次都是新的输入流，可以重复读取
     * @return
     */
    InputStream getInputStream();

    void close();

}
