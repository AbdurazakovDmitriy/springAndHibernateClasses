package com.dmitriyabdurazakov.springboot.service.config.customEditors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Map;

public class StringToMapEditor extends PropertyEditorSupport {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getAsText() {
        Map<String, String> map = (Map<String, String>) getValue();
        return map.toString();
    }

    @SneakyThrows
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            Map<String, String> map = objectMapper.readValue(text, new TypeReference<Map<String, String>>() {
            });
            setValue(map);
        }
    }
}
