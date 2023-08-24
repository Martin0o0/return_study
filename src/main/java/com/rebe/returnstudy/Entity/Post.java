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
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private Member member;

//    //지연로딩 전략으로 Post 엔티티를 반환받을 때, 실제 객체가 아닌,
//    //프록시 객체[모의 객체]로 PostLike 엔티티를 가져온다.
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE})
    @ToString.Exclude
    private List<PostLike> diaryLikes = new ArrayList<>();


}
