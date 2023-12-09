package dev.afartur.labseq.controller;

import dev.afartur.labseq.dto.LabSeqCacheResponse;
import dev.afartur.labseq.dto.LabSeqResponse;
import dev.afartur.labseq.service.LabSeqService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@Tag(name = "LabSeq", description = "LabSeq sequence API")
@RestController
@RequestMapping("/labseq")
public class LabSeqController {

    private final LabSeqService labSeqService;

    public LabSeqController(LabSeqService labSeqService) {
        this.labSeqService = labSeqService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LabSeq sequence value of N and the execution time in seconds",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LabSeqResponse.class),
                            examples = @ExampleObject(value = "{'value': 3, 'executionTime': 0.0}"))),
            @ApiResponse(responseCode = "400", description = "Invalid N (N >= 0)", content = @Content)
    })
    @Operation(summary = "Get the LabSeq sequence value of N")
    @GetMapping("/{n}")
    ResponseEntity<LabSeqResponse> getLabSeq(@PathVariable Integer n) {
        long startTime = System.nanoTime();

        BigInteger value = labSeqService.computeLabSeq(n);

        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1e9;

        return ResponseEntity.ok().body(new LabSeqResponse(value, executionTime));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cache status information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LabSeqCacheResponse.class),
                            examples = @ExampleObject(value = "{'size': 3, 'hits': 1, 'misses': 8}"))),
    })
    @Operation(summary = "Get the cache status")
    @GetMapping("/cache")
    ResponseEntity<LabSeqCacheResponse> getLabSeqCacheStatus() {
        return ResponseEntity.ok().body(labSeqService.getCacheStatus());
    }
}
