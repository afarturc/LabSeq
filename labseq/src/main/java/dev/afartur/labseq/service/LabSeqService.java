package dev.afartur.labseq.service;

import dev.afartur.labseq.cache.LabSeqCache;
import dev.afartur.labseq.exception.InputException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class LabSeqService {
    private final LabSeqCache labSeqCache;

    public LabSeqService(LabSeqCache labSeqCache) {
        this.labSeqCache = labSeqCache;
    }

    public BigInteger computeLabSeq(Integer n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative.");
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

        if (n > 5000) {
            return computeLabSeq(n / 2);
        }

        BigInteger result1 = computeLabSeq(n - 4);
        BigInteger result2 = computeLabSeq(n - 3);

        result = result1.add(result2);

        labSeqCache.put(n, result);

        return result;
    }
}
