package dev.afartur.labseq;

import dev.afartur.labseq.cache.LabSeqCache;
import dev.afartur.labseq.exception.InputException;
import dev.afartur.labseq.service.LabSeqService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class LabSeqServiceTest {
    @InjectMocks
    private LabSeqService labSeqService;
    @Mock
    private LabSeqCache labSeqCache;

    @Test
    public void givenInvalidInteger_whenCalculatingLabSeq_thenThrowException() {
        assertThatThrownBy(() -> {
            labSeqService.computeLabSeq(-10);
        }).isInstanceOf(InputException.class);
    }

    @Test
    public void givenValidInteger_whenCalculatingLabSeq_withoutCache_theUpdateCache_andReturnValue() {
        when(labSeqCache.get(10)).thenReturn(null);

        assertThat(labSeqService.computeLabSeq(10))
                .isEqualTo(3);

        this.checkCacheUpdate(10);
        this.checkCacheUpdate(7);
        this.checkCacheUpdate(6);
    }

    @Test
    public void givenValidInteger_whenCalculatingLabSeq_withCache_thenCheckCache_andReturnValue() {
        when(labSeqCache.get(10)).thenReturn(BigInteger.valueOf(3));

        assertThat(labSeqService.computeLabSeq(10))
                .isEqualTo(3);

        this.checkCacheHit(10);
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
