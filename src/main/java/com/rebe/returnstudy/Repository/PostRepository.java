package com.rebe.returnstudy.Repository;

import com.rebe.returnstudy.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
