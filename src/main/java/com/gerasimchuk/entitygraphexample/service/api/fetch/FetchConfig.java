package com.gerasimchuk.entitygraphexample.service.api.fetch;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public interface FetchConfig {
    List<FetchConfigEntry> getFieldsToBeFetched();

    @Data
    @AllArgsConstructor
    class FetchConfigEntry {
        private Class<?> entityClass;
        private int level;
        private List<String> fetchFields;
    }
}
