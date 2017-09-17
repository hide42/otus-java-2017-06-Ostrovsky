package cache;

import java.lang.ref.SoftReference;

public class MyElement<V>{
    private final SoftReference<V> valueReference;
    private final long creationTime;
    private long lastAccessTime;


    public MyElement(V value) {
        this.valueReference = new SoftReference<>(value);
        this.creationTime = getCurrentTime();
        this.lastAccessTime = getCurrentTime();
    }

    protected long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public SoftReference<V> getValueReference() {
        return valueReference;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setAccessed() {
        lastAccessTime = getCurrentTime();
    }
}
