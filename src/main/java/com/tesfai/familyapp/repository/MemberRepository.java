package com.tesfai.familyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tesfai.familyapp.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

}
