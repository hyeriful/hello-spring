package com.hello.hellospring.controller;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Controller를 통해서 외부 요청을 받고
// Service에서 비지니스 로직을 만들고
// Repository에서 데이터를 저장

@Controller
public class MemberController { //spring container가  뜰 때 MemberController생성 됨
    private final MemberService memberService;

// spring container에 등록(하나만)을 하고 container에서 받아서 쓰도록
    // 연결해주는 방법은 생성자!
    @Autowired  //spring container에 있는 MemberService를 가져다가 연결시켜줌
    //MemberController가 생성이 될 때 스프링 빈에 등록되어 있는 MemberService 객체를 가져다가 연결, 넣어줌 (DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createFrom() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
//        System.out.println("member = "+ member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
