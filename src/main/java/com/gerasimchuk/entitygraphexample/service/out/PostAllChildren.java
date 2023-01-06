package com.gerasimchuk.entitygraphexample.service.out;

import com.gerasimchuk.entitygraphexample.service.api.out.Comment;
import com.gerasimchuk.entitygraphexample.service.api.out.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostAllChildren implements Post {
    private final String content;
    private final List<Comment> comments;
}
