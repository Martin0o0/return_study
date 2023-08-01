package com.rebe.returnstudy.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
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


}
