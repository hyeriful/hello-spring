package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


class MemoryMemberRepositoryTest {  //다른 곳에서 사용할 거 아니기 때문에 public안해도 됨
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //각 method가 끝날때마다 실행
    public void afterEach() {
        repository.clearStore();
    }

    // Test 순서는 구현한 Method 순이 아니다!!
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findByid(member.getId()).get(); //Optional에서 값을 꺼낼 때 get 사용

        //assertj
        Assertions.assertThat(member).isEqualTo(result);

        // junit
//        Assertions.assertEquals(member, result);
//        Assertions.assertEquals(member, null);    //오류 뜸
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result =  repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
