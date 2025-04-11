package com.effort.util;

import java.util.*;

public class FormPayloadBuilder {

    public static Map<String, Object> buildFormPayload(Map<String, Object> originalPayload) {
        Map<String, Object> newPayload = new HashMap<>();
        Map<String, Object> formsSection = new HashMap<>();

      
        List<Map<String, Object>> forms = (List<Map<String, Object>>) originalPayload.getOrDefault("forms", new ArrayList<>());
        formsSection.put("added", forms);

        newPayload.put("forms", formsSection);

        return newPayload;
    }
}
