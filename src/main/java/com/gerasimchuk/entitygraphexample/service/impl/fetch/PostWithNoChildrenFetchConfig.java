package com.gerasimchuk.entitygraphexample.service.impl.fetch;

import com.gerasimchuk.entitygraphexample.domain.entity.Post;
import com.gerasimchuk.entitygraphexample.service.api.fetch.FetchConfig;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostWithNoChildrenFetchConfig implements FetchConfig {

    @Override
    public List<FetchConfigEntry> getFieldsToBeFetched() {
        FetchConfigEntry postsConfig = new FetchConfigEntry(
                Post.class, 0, List.of("content")
        );
        return List.of(postsConfig);
    }
}
