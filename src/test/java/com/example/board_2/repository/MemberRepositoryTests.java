package com.example.board_2.repository;

import com.example.board_2.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,100).forEach(i ->{
            Member member = Member.builder()
                    .email("user"+i+"@aaa.com")
                    .name("user"+i)
                    .password("1111")
                    .build();

            memberRepository.save(member);
        });
    }
}
