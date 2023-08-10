package com.rebe.returnstudy.Repository;

import com.rebe.returnstudy.Entity.MemberDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDetailsRepository extends JpaRepository<MemberDetails, Long> {
}
