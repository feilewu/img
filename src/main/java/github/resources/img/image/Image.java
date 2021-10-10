package github.resources.img.image;

public interface Image {

    String getName();

    String getPath();

    /**
     * 保存图片
     */
    void write();

    /**
     * 读取图片
     * @return
     */
    byte[] readBytes();

}
