package dev.afartur.labseq.controller;

import dev.afartur.labseq.dto.LabSeqCacheResponse;
import dev.afartur.labseq.dto.LabSeqResponse;
import dev.afartur.labseq.service.LabSeqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/labseq")
public class LabSeqController {

    private final LabSeqService labSeqService;

    public LabSeqController(LabSeqService labSeqService) {
        this.labSeqService = labSeqService;
    }

    @GetMapping("/{n}")
    ResponseEntity<LabSeqResponse> getLabSeq(@PathVariable Integer n) {
        long startTime = System.nanoTime();

        BigInteger value = labSeqService.computeLabSeq(n);

        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1e9;

        return ResponseEntity.ok().body(new LabSeqResponse(value, executionTime));
    }

    @GetMapping("/cache")
    ResponseEntity<LabSeqCacheResponse> getLabSeqCacheStatus() {
        return ResponseEntity.ok().body(labSeqService.getCacheStatus());
    }
}
