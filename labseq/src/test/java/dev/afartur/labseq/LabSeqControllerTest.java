package dev.afartur.labseq;

import dev.afartur.labseq.controller.LabSeqController;
import dev.afartur.labseq.dto.LabSeqCacheResponse;
import dev.afartur.labseq.exception.InputException;
import dev.afartur.labseq.service.LabSeqService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LabSeqController.class)
@AutoConfigureMockMvc
class LabSeqControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LabSeqService labSeqService;

    @Test
    void givenValidInteger_whenCalculatingLabSeq_thenReturnLabSeqResponse() throws Exception {
        when(labSeqService.computeLabSeq(anyInt())).thenReturn(BigInteger.valueOf(12345));

        mockMvc.perform(get("/labseq/{n}", 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(12345));
    }

    @Test
    void givenInvalidInteger_whenCalculatingLabSeq_thenReturnExceptionResponse() throws Exception {
        when(labSeqService.computeLabSeq(-10)).thenThrow(new InputException("Input must be non-negative."));

        mockMvc.perform(get("/labseq/{n}", -10))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCacheStats() throws Exception {
        LabSeqCacheResponse cacheResponse = new LabSeqCacheResponse(10, 2, 8);

        when(labSeqService.getCacheStatus()).thenReturn(cacheResponse);

        mockMvc.perform(get("/labseq/cache"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.hits").value(2))
                .andExpect(jsonPath("$.misses").value(8));
    }
}
