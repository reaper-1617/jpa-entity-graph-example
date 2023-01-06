package com.gerasimchuk.entitygraphexample.domain.repository;

import com.gerasimchuk.entitygraphexample.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
