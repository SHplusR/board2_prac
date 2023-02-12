package com.example.board_2.repository;

import com.example.board_2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member,String> {
}
