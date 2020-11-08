package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    //실무에서는 동시성 문제가 있어서, 공유되는 변수일때는 ConcurrentHashMap 사용해야 함
    private static Map<Long, Member> store = new HashMap<>();
    //이것도 동시성 문제 고려해서 AtomicLong 해야함
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByid(Long id) {
        // 리턴값이 null일 수도 있으면 'Optional.ofNullable' 하면 null이어도 감쌀수있다
        // 감싸서 봔환하면 클라이언트에서 뭘 할 수 있음
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 결과가 Optional로 반환. 하나 찾으면 반환. 없으면 Optional에 null이 포함돼서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
