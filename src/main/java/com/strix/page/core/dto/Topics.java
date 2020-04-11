package com.strix.page.core.dto;

import lombok.Value;

import java.util.List;

@Value
public class Topics {

    private String tag;
    private List<String> values;

}
