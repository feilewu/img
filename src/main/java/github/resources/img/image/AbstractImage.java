package github.resources.img.image;

public abstract class AbstractImage implements Image{

    protected byte[] bytes;

    protected String name;

    /**
     * 目标存放路径
     */
    protected String path;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public byte[] readBytes() {
        return bytes;
    }
}
