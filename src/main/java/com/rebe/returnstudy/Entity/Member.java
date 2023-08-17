package com.rebe.returnstudy.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer studentId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String generation;
    @Column(nullable = false)
    private String club;

    @OneToOne(mappedBy = "member")
    @ToString.Exclude //순환참조 방지
    private MemberDetails memberDetails;

    //Fetch전략을 EAGER로 두어 Posts를 같이 가져온다.
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    @ToString.Exclude //순환참조
    private List<Post> posts = new ArrayList<>();

    //지연로딩 전략으로 Member 엔티티를 반환받을 때, 실제 객체가 아닌,
    //프록시 객체[모의 객체]로 PostLike 엔티티를 가져온다.
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @ToString.Exclude //순환참조
    private List<PostLike> postLikes = new ArrayList<>();
}
