package com.gardensmc.gardensrpg.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public abstract class Cache<K, V> {

    protected final LoadingCache<K, V> cache;

    public Cache() {
        cache = Caffeine.newBuilder()
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build(this::createCacheEntry);
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void remove(K key) {
        cache.invalidate(key);
    }

    public void removeAll() {
        cache.invalidateAll();
    }

    abstract V createCacheEntry(K key);

}
