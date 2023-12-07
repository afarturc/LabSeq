package dev.afartur.labseq.cache;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Component
public class LabSeqCache {
    private final Map<Integer, BigInteger> cache = new HashMap<>();
    private int cacheHits = 0;
    private int cacheMisses = 0;

    public BigInteger get(Integer n) {
        if (cache.containsKey(n)) {
            cacheHits++;
            return cache.get(n);
        } else {
            cacheMisses++;
            return null;
        }
    }

    public void put(Integer n, BigInteger result) {
        cache.put(n, result);
    }

    public int getCacheSize() {
        return cache.size();
    }

    public int getCacheHits() {
        return cacheHits;
    }

    public int getCacheMisses() {
        return cacheMisses;
    }
}
