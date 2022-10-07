package github.resources.img.core;

import java.util.List;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/10/7 14:33
 **/
public class Page<T> {

    protected List<T> records;

    /**
     * 总数
     */
    protected long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    protected long size = 10;

    /**
     * 当前页
     */
    protected long current = 1;

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public List<T> getRecords() {
        return records;
    }

    public long getTotal() {
        return total;
    }

    public long getSize() {
        return size;
    }

    public long getCurrent() {
        return current;
    }
}
