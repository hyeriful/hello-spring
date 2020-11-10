package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface가 interface를 받을 때는 extends 사용
// interface는 다중상속 가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    //스프링 데이터 JPA가 JpaRepository를 extends하면 구현체를 자동으로 만들어주고 스프링 빈 자동 등록
    @Override
    Optional<Member> findByName(String name);
}
