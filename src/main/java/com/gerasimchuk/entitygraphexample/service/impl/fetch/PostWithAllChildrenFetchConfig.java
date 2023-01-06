package com.gerasimchuk.entitygraphexample.service.impl.fetch;

import com.gerasimchuk.entitygraphexample.domain.entity.Comment;
import com.gerasimchuk.entitygraphexample.domain.entity.Post;
import com.gerasimchuk.entitygraphexample.domain.entity.Reaction;
import com.gerasimchuk.entitygraphexample.service.api.fetch.FetchConfig;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostWithAllChildrenFetchConfig implements FetchConfig {

    @Override
    public List<FetchConfigEntry> getFieldsToBeFetched() {
        FetchConfigEntry postsConfig = new FetchConfigEntry(
                Post.class, 0, List.of("content", "comments")
        );

        FetchConfigEntry commentsConfig = new FetchConfigEntry(
                Comment.class, 1, List.of("content", "reactions")
        );

        FetchConfigEntry reactionsConfig = new FetchConfigEntry(
                Reaction.class, 2, List.of("type")
        );
        return List.of(postsConfig, commentsConfig, reactionsConfig);
    }
}
