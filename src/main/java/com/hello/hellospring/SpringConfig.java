package com.hello.hellospring;

import com.hello.hellospring.aop.TimeTraceAop;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링 빈 등록하는 방법 2
@Configuration
public class SpringConfig {

    /*
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
     */

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean   //repo, serv 처럼 정형화 된 것이 아니기 때문에, 인지할 수 있게 따로 이렇게 스프링빈에 등록해주는 방식 선호
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
}
