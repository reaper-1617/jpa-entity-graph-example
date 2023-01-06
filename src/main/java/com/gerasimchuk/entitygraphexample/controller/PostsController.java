package com.gerasimchuk.entitygraphexample.controller;

import com.gerasimchuk.entitygraphexample.service.api.PostService;
import com.gerasimchuk.entitygraphexample.service.api.fetch.FetchConfig;
import com.gerasimchuk.entitygraphexample.service.impl.fetch.PostWithAllChildrenFetchConfig;
import com.gerasimchuk.entitygraphexample.service.impl.fetch.PostWithNoChildrenFetchConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostService postService;

    @GetMapping("/allchildren")
    public ResponseEntity<?> getPostsAllChildren(){
        FetchConfig fetchConfig = new PostWithAllChildrenFetchConfig();
        var result = postService.getPosts(fetchConfig);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/nochildren")
    public ResponseEntity<?> getPostsNoChildren(){
        FetchConfig fetchConfig = new PostWithNoChildrenFetchConfig();
        var result = postService.getPosts(fetchConfig);
        return ResponseEntity.ok(result);
    }

}
