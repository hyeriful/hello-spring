package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;
// 코드짜면서 alt+enter 자동으로 import

public interface MemberRepository {
    Member save(Member member); //회원 저장하면 저장된 회원 반환
    //null일 가능성이 있으면 Optional로 감싸서 반환
    Optional<Member> findByid(Long id); //Optional: null을 반환할 수도 있는데, 이때 null을 처리하는 방법에서 Optional로 감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
