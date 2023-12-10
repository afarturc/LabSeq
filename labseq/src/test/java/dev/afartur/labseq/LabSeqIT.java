package dev.afartur.labseq;

import dev.afartur.labseq.dto.ExceptionResponse;
import dev.afartur.labseq.dto.LabSeqCacheResponse;
import dev.afartur.labseq.dto.LabSeqResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LabSeqIT {
    private final static String BASE_URI = "http://localhost:";

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void givenInvalidInteger_whenCalculatingLabSeq_thenReturnExceptionResponse() throws Exception {
        ResponseEntity<ExceptionResponse> response = testRestTemplate.exchange(BASE_URI + randomServerPort + "/labseq/-1", HttpMethod.GET, null, ExceptionResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(response.getBody()).message()).isEqualTo("Input must be non-negative.");
    }

    @Test
    void givenValidInteger_whenCalculatingLabSeq_thenReturnLabSeqResponse_andCheckCacheStatus() throws Exception {
        ResponseEntity<LabSeqResponse> labSeqResponse = testRestTemplate.exchange(BASE_URI + randomServerPort + "/labseq/10", HttpMethod.GET, null, LabSeqResponse.class);
        assertThat(labSeqResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(labSeqResponse.getBody()).value()).isEqualTo(3);

        ResponseEntity<LabSeqCacheResponse> cacheResponse = testRestTemplate.exchange(BASE_URI + randomServerPort + "/labseq/cache", HttpMethod.GET, null, LabSeqCacheResponse.class);
        assertThat(cacheResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(cacheResponse.getBody()).size()).isEqualTo(8);
        assertThat(Objects.requireNonNull(cacheResponse.getBody()).hits()).isEqualTo(1);
        assertThat(Objects.requireNonNull(cacheResponse.getBody()).misses()).isEqualTo(8);
    }
}
