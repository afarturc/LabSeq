package dev.afartur.labseq.dto;

import java.math.BigInteger;

public record LabSeqResponse(
        BigInteger value,
        double executionTime
) {}
