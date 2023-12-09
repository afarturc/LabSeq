package dev.afartur.labseq.cache;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Cache class for the labseq sequence.
 */
@Component
public class LabSeqCache {
    private final Map<Integer, BigInteger> cache = new HashMap<>();
    private int cacheHits = 0;
    private int cacheMisses = 0;

    /**
     * If the cache contains the value corresponding to the index n it returns the value and increments the hits.
     * Otherwise, it will return a null value and increment the misses.
     * @param n index of the labseq sequence.
     * @return a BigInteger corresponding to the labseq sequence value of index n.
     */
    public BigInteger get(Integer n) {
        if (cache.containsKey(n)) {
            cacheHits++;
            return cache.get(n);
        } else {
            cacheMisses++;
            return null;
        }
    }

    /**
     * Updates the cache value of the index n with the result.
     * If the value does not exist it will create that entry in the cache.
     * @param n index of the labseq sequence.
     * @param result the labseq sequence value corresponding to the index n.
     */
    public void put(Integer n, BigInteger result) {
        cache.put(n, result);
    }

    /**
     * Returns the number of entries in the cache.
     * @return size of the cache.
     */
    public int getCacheSize() {
        return cache.size();
    }

    /**
     * Returns the number of times the cache is called, and it has the value.
     * @return number of times cache had the value asked.
     */
    public int getCacheHits() {
        return cacheHits;
    }

    /**
     * Returns the number of times the cache is called, and it does not have the value.
     * @return number of times cache did not have the value asked.
     */
    public int getCacheMisses() {
        return cacheMisses;
    }
}
