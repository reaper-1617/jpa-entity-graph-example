package com.gerasimchuk.entitygraphexample.util;

import com.gerasimchuk.entitygraphexample.common.enumeration.ReactionType;
import com.gerasimchuk.entitygraphexample.domain.entity.Comment;
import com.gerasimchuk.entitygraphexample.domain.entity.Post;
import com.gerasimchuk.entitygraphexample.domain.entity.Reaction;
import com.gerasimchuk.entitygraphexample.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabasePreparationService {

    private final PostRepository postRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void prepareData() {
        log.info("Preparing data for saving ...");
        Reaction reaction1 = Reaction.builder()
                .type(ReactionType.LIKE)
                .build();

        Reaction reaction2 = Reaction.builder()
                .type(ReactionType.DISLIKE)
                .build();

        Reaction reaction3 = Reaction.builder()
                .type(ReactionType.LIKE)
                .build();

        Reaction reaction4 = Reaction.builder()
                .type(ReactionType.DISLIKE)
                .build();

        Comment comment1 = Comment.builder()
                .content("New comment " + UUID.randomUUID())
                .build();

        comment1.addReaction(reaction1);
        comment1.addReaction(reaction2);

        Comment comment2 = Comment.builder()
                .content("New comment " + UUID.randomUUID())
                .build();

        comment2.addReaction(reaction3);
        comment2.addReaction(reaction4);


        Post post = Post.builder()
                .content("New post " + UUID.randomUUID())
                .build();

        post.addComment(comment1);
        post.addComment(comment2);

        log.info("Data prepared, saving ...");
        postRepository.save(post);
        log.info("Data saved successfully");
    }
}
