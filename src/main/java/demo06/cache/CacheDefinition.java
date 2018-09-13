package demo06.cache;

public class CacheDefinition {
    private String name;

    private int seconds;

    private String tag;

    public String getName() {
        return name;
    }

    public CacheDefinition setName(String name) {
        this.name = name;
        return this;
    }

    public int getSeconds() {
        return seconds;
    }

    public CacheDefinition setSeconds(int seconds) {
        this.seconds = seconds;
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
