package com.gerasimchuk.entitygraphexample.service.impl;

import com.gerasimchuk.entitygraphexample.service.api.PostService;
import com.gerasimchuk.entitygraphexample.service.api.fetch.FetchConfig;
import com.gerasimchuk.entitygraphexample.service.api.out.Comment;
import com.gerasimchuk.entitygraphexample.service.api.out.Post;
import com.gerasimchuk.entitygraphexample.service.impl.fetch.EntityGraphFactory;
import com.gerasimchuk.entitygraphexample.service.impl.fetch.PostWithAllChildrenFetchConfig;
import com.gerasimchuk.entitygraphexample.service.impl.fetch.PostWithNoChildrenFetchConfig;
import com.gerasimchuk.entitygraphexample.service.out.CommentWithChildren;
import com.gerasimchuk.entitygraphexample.service.out.PostAllChildren;
import com.gerasimchuk.entitygraphexample.service.out.PostNoChildren;
import com.gerasimchuk.entitygraphexample.service.out.ReactionOut;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final EntityGraphFactory entityGraphFactory;
    private final EntityManager entityManager;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Post> getPosts(FetchConfig fetchConfig) {
        var graph = entityGraphFactory.getGraph(fetchConfig, Post.class);
        var posts = entityManager.createQuery("select p from Post p", com.gerasimchuk.entitygraphexample.domain.entity.Post.class)
                .setHint("jakarta.persistence.fetchgraph", graph) // 'jakarta' instead of 'javax'
                .getResultList();
        return posts.stream().map(p -> convert(p, fetchConfig)).toList();
    }

    private Post convert(com.gerasimchuk.entitygraphexample.domain.entity.Post post, FetchConfig fetchConfig) {
        // here should be conversion logic according fields configured in fetchConfig
        // but we replace it with 'if' for simplicity of an example

        if (fetchConfig.getClass() == PostWithAllChildrenFetchConfig.class) {
            List<Comment> comments = post.getComments().stream()
                    .map(c -> new CommentWithChildren(c.getContent(), c.getReactions().stream().map(r -> new ReactionOut(r.getType())).collect(Collectors.toList())))
                    .collect(Collectors.toList());
            Post postOut = new PostAllChildren(
                    post.getContent(),
                    comments
                    );
            return postOut;
        }
        if (fetchConfig.getClass() == PostWithNoChildrenFetchConfig.class) {
            return new PostNoChildren(post.getContent());
        }
        throw new IllegalArgumentException();
    }
}
