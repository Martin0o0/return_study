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
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String category;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id") //Post 엔티티에서 외래키를 관리하게 됨.
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

}
