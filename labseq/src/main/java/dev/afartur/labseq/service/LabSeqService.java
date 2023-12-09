package dev.afartur.labseq.service;

import dev.afartur.labseq.cache.LabSeqCache;
import dev.afartur.labseq.dto.LabSeqCacheResponse;
import dev.afartur.labseq.exception.InputException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Service class for the labseq sequence calculation and cache information.
 */
@Service
public class LabSeqService {
    private final LabSeqCache labSeqCache;

    public LabSeqService(LabSeqCache labSeqCache) {
        this.labSeqCache = labSeqCache;
    }

    /**
     * Returns the labseq sequence value of the specified index n.
     * @param n index of the sequence.
     * @return labseq sequence value corresponding to the provided index.
     */
    public BigInteger computeLabSeq(Integer n) {
        if (n < 0) {
            throw new InputException("Input must be non-negative.");
        }

        BigInteger result = labSeqCache.get(n);
        if (result != null) {
            return result;
        }

        if (n <= 3) {
            BigInteger baseCase = BigInteger.valueOf(n % 2);
            labSeqCache.put(n, baseCase);
            return baseCase;
        }

        if (n > 7000) {
            return computeLabSeq(n / 2);
        }

        BigInteger result1 = computeLabSeq(n - 4);
        BigInteger result2 = computeLabSeq(n - 3);

        result = result1.add(result2);

        labSeqCache.put(n, result);

        return result;
    }

    /**
     * Returns the cache status information for the labseq sequence.
     * @return size, hits and misses
     */
    public LabSeqCacheResponse getCacheStatus() {
        return new LabSeqCacheResponse(labSeqCache.getCacheSize(), labSeqCache.getCacheHits(), labSeqCache.getCacheMisses());
    }
}
