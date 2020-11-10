package com.hello.hellospring;

import com.hello.hellospring.repository.JdbcTemplateMemberRepository;
import com.hello.hellospring.repository.JpaMemberRepository;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import com.hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//스프링 빈 등록하는 방법 2
@Configuration
public class SpringConfig {

    private EntityManager em;
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }


    @Bean
    public MemberService memberService() {  //이것을 호출해서 스프링 빈에 등록해줌
        return new MemberService(memberRepository());   //스프링 빈에 등록되어 있는 MemberRepository를 MemberService에 넣어줌
    }

    @Bean
    public MemberRepository memberRepository() {
        //구현체를 new해주기 (interface는 new안됨)
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
