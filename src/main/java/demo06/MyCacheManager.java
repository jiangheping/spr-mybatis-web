package demo06;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;

public class MyCacheManager extends AbstractCacheManager {
    private Collection<? extends MyCache> caches;

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return this.caches;
    }

    public void setCaches(Collection<? extends MyCache> caches) {
        this.caches = caches;
    }
}
