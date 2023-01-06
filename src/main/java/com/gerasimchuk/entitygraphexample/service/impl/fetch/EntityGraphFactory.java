package com.gerasimchuk.entitygraphexample.service.impl.fetch;

import com.gerasimchuk.entitygraphexample.service.api.fetch.FetchConfig;
import jakarta.persistence.EntityGraph;

public interface EntityGraphFactory {
    <T> EntityGraph<T> getGraph(FetchConfig fetchConfig, Class<T> entityClass);
}
