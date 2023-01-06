package com.gerasimchuk.entitygraphexample.service.api;

import com.gerasimchuk.entitygraphexample.service.api.fetch.FetchConfig;
import com.gerasimchuk.entitygraphexample.service.api.out.Post;

import java.util.List;

public interface PostService {
    List<Post> getPosts(FetchConfig fetchConfig);
}
