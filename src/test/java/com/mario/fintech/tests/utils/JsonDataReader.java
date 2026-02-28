package com.mario.fintech.tests.utils;

import com.mario.fintech.tests.models.CreditTestData;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonDataReader {

    private static final String DATA_PATH = "src/test/resources/data/";

    public static List<CreditTestData> getTestData(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                new File(DATA_PATH + fileName),
                new TypeReference<List<CreditTestData>>() {
                }
        );
    }

}
