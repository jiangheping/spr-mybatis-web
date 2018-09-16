package demo06.cache;

public class CacheDefinition {
    private String name;//缓存名称

    private int duration;//缓存有效时间

    private String tag;//缓存标签

    public String getName() {
        return name;
    }

    public CacheDefinition setName(String name) {
        this.name = name;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public CacheDefinition setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public CacheDefinition setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
