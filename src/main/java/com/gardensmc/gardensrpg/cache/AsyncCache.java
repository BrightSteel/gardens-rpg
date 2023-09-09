package com.gardensmc.gardensrpg.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public abstract class AsyncCache<K, V> {

    private final AsyncLoadingCache<K, V> cache;

    public AsyncCache() {
        cache = Caffeine.newBuilder()
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .buildAsync((key, executor) -> createCacheEntry(key));
    }

    public CompletableFuture<V> get(K key) {
        return cache.get(key);
    }

    public void remove(K key) {
        cache.synchronous().invalidate(key);
    }

    public void removeAll() {
        cache.synchronous().invalidateAll();
    }

    abstract CompletableFuture<V> createCacheEntry(K key);

}
