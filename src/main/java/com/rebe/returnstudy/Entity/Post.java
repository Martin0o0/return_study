package com.rebe.returnstudy.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_PK")
    @ToString.Exclude
    private Member member;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<PostLike> diaryLikes = new ArrayList<>();


}
