package dev.afartur.labseq;

import dev.afartur.labseq.cache.LabSeqCache;
import dev.afartur.labseq.dto.LabSeqCacheResponse;
import dev.afartur.labseq.exception.InputException;
import dev.afartur.labseq.service.LabSeqService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LabSeqServiceTest {
    @InjectMocks
    private LabSeqService labSeqService;
    @Mock
    private LabSeqCache labSeqCache;

    @Test
    void givenInvalidInteger_whenCalculatingLabSeq_thenThrowException() {
        assertThatThrownBy(() -> {
            labSeqService.computeLabSeq(-10);
        }).isInstanceOf(InputException.class);
    }

    @Test
    void givenValidInteger_whenCalculatingLabSeq_withoutCache_theUpdateCache_andReturnValue() {
        when(labSeqCache.get(10)).thenReturn(null);

        assertThat(labSeqService.computeLabSeq(10))
                .isEqualTo(3);

        this.checkCacheUpdate(10);
        this.checkCacheUpdate(7);
        this.checkCacheUpdate(6);
    }

    @Test
    void givenValidInteger_whenCalculatingLabSeq_withCache_thenCheckCache_andReturnValue() {
        when(labSeqCache.get(10)).thenReturn(BigInteger.valueOf(3));

        assertThat(labSeqService.computeLabSeq(10))
                .isEqualTo(3);

        this.checkCacheHit(10);
    }

    @Test
    void checkCacheStatus(){
        when(labSeqCache.getCacheSize()).thenReturn(9);
        when(labSeqCache.getCacheHits()).thenReturn(1);
        when(labSeqCache.getCacheMisses()).thenReturn(8);

        LabSeqCacheResponse response = labSeqService.getCacheStatus();
        assertThat(response).isInstanceOf(LabSeqCacheResponse.class);
        assertThat(response.size()).isEqualTo(9);
        assertThat(response.hits()).isEqualTo(1);
        assertThat(response.misses()).isEqualTo(8);
    }

    private void checkCacheHit(Integer value) {
        Mockito.verify(labSeqCache, times(1))
                .get(value);
    }

    private void checkCacheUpdate(Integer value) {
        Mockito.verify(labSeqCache, times(1))
                .put(Mockito.eq(value), Mockito.any());
    }
}
