package dev.afartur.labseq.dto;

/**
 * Record for an labseq cache status response.
 * @param size number of entries in the cache.
 * @param hits number of times the cache had the value required.
 * @param misses number of times the cache did not have the value required.
 */
public record LabSeqCacheResponse(
        Integer size,
        Integer hits,
        Integer misses
) {
}
