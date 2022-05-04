package github.resources.img.manager.bo;

public class ImageBo {

    private String localPath;

    private String suffix;

    private String uri;

    private String name;

    private byte[] content;

    /**
     * 所属人ID
     */
    private long owner;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getUri() {
        return uri;
    }

    public byte[] getContent() {
        return content;
    }

    public long getOwner() {
        return owner;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
