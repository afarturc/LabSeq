package dev.afartur.labseq.dto;

import java.math.BigInteger;

/**
 * Record of the labseq sequence calculation response.
 * @param value labseq sequence value of a index n.
 * @param executionTime time spent calculating the labseq sequence value.
 */
public record LabSeqResponse(
        BigInteger value,
        double executionTime
) {}
