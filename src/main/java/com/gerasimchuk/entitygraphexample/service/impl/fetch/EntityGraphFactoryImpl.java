package com.gerasimchuk.entitygraphexample.service.impl.fetch;

import com.gerasimchuk.entitygraphexample.domain.entity.Post;
import com.gerasimchuk.entitygraphexample.service.api.fetch.FetchConfig;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntityGraphFactoryImpl implements EntityGraphFactory {


    private final EntityManager entityManager;
    private final List<FetchConfig> fetchConfigs;
    private Map<Class<? extends FetchConfig>, FetchConfig> configMap;

    @PostConstruct
    void postConstruct() {
        configMap = Collections.unmodifiableMap(fetchConfigs.stream().collect(Collectors.toMap(
                FetchConfig::getClass,
                Function.identity()
        )));
    }

    @Override
    public <T> EntityGraph<T> getGraph(FetchConfig fetchConfig, Class<T> entityClass) {
        var res = configMap.get(fetchConfig.getClass());
        if (res == null) {
            throw new IllegalArgumentException("Could not found fetch config");
        }
        var fields = fetchConfig.getFieldsToBeFetched();
        return buildFromFieldsList(fields);
    }

    private <T> EntityGraph<T> buildFromFieldsList(List<FetchConfig.FetchConfigEntry> fetchConfigEntries){
        // here custom logic for graph building should be.
        // for the example purposes replaced with simple 'if' constructions

        if (fetchConfigEntries.size() == 1) {
            return (EntityGraph<T>) getGraphForAllChildrenFetch();
        }
        if (fetchConfigEntries.size() == 3) {
            return (EntityGraph<T>) getGraphForNoChildrenFetch();
        }
        throw new UnsupportedOperationException("Could not build entity graph");
    }

    private EntityGraph<Post> getGraphForAllChildrenFetch() {
        var graph = entityManager.createEntityGraph(Post.class);
        graph.addAttributeNodes("content");
        var commentsSubGraph = graph.addSubgraph("comments");
        commentsSubGraph.addAttributeNodes("content");

        var reactionsSubgraph = commentsSubGraph.addSubgraph("reactions");
        reactionsSubgraph.addAttributeNodes("type");
        return graph;
    }

    private EntityGraph<Post> getGraphForNoChildrenFetch() {
        var graph = entityManager.createEntityGraph(Post.class);
        graph.addAttributeNodes("content");
        return graph;
    }
}
