package dev.afartur.labseq.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigInteger;

public class BigIntegerSerializer extends JsonSerializer<BigInteger> {

    @Override
    public void serialize(BigInteger bigInteger, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(bigInteger.toString());
    }
}
