package com.linkener.crudtest.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonTestUtil {

    public static String toJson(final Object o) {
        String result;
        try {
            result = new ObjectMapper().writeValueAsString(o);
        } catch (final Exception e) {
            // TODO Loguear error
            result = null;
        }
        return result;
    }
}
