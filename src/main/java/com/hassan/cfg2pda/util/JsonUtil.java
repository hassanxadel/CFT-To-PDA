package com.hassan.cfg2pda.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static String toJson(Object o) {
        try { return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(o); }
        catch (Exception e) { throw new RuntimeException(e); }
    }
}
