package com.rebe.returnstudy.Repository;

import com.rebe.returnstudy.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
