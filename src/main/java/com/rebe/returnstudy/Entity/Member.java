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

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    @ToString.Exclude //순환참조
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<PostLike> postLikes = new ArrayList<>();
}
