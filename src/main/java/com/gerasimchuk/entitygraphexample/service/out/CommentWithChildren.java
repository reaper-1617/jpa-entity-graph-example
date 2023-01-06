package com.gerasimchuk.entitygraphexample.service.out;

import com.gerasimchuk.entitygraphexample.service.api.out.Comment;
import com.gerasimchuk.entitygraphexample.service.api.out.Reaction;
import lombok.Data;

import java.util.List;

@Data
public class CommentWithChildren implements Comment {
    private final String content;
    private final List<Reaction> reactions;
}
