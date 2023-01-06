package com.gerasimchuk.entitygraphexample.service.out;

import com.gerasimchuk.entitygraphexample.service.api.out.Post;
import lombok.Data;

@Data
public class PostNoChildren implements Post {
    private final String content;
}
