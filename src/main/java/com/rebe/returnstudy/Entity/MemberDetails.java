package com.rebe.returnstudy.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetails {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = false, nullable = false)
    private String email;

    @Column(unique = false, nullable = false)
    private String phoneNumber;

    @Column(nullable = true, length = 255)
    private String StatusMsg;

    @Column
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
