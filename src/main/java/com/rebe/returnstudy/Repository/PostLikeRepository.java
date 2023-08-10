package com.rebe.returnstudy.Repository;


import com.rebe.returnstudy.Entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    List<PostLike> findAllByMemberId(Long id);

    List<PostLike> findAllByPostId(Long id);
}
