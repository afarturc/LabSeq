package dev.afartur.labseq.dto;

public record LabSeqCacheResponse(
        Integer size,
        Integer hits,
        Integer misses
) {
}
