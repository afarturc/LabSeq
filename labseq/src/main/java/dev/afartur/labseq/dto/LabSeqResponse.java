package dev.afartur.labseq.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigInteger;

/**
 * Record of the labseq sequence calculation response.
 * @param value labseq sequence value of a index n.
 * @param executionTime time spent calculating the labseq sequence value.
 */
public record LabSeqResponse(
        @JsonSerialize(using = BigIntegerSerializer.class)
        BigInteger value,
        double executionTime
) {}
