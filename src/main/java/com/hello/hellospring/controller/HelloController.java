package com.hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    //웹 어플리케이션에서 /hello 들어오면 이 메소드 호출 (GetMapping의 Get은 get method)
    public String hello(Model model) {  //MVC에서 M(model). spring이 model이란 것을 만들어서 넣어줌
        model.addAttribute("data", "spring!!");
        return "hello"; // templates디렉토리로 가서 hello.html찾아서 렌더링
    }

    // MVC
    // v: 화면과 관련된 일
    // c: 비지니스 로직과 서버 뒷단의 관련된 일은 컨트롤러나 뒷단 비지니스 로직에서 처리
    // m: 모델에다가 관련된 (화면에) 필요한 것을 담아서 화면에 넘겨줌
    @GetMapping("hello-mvc")
    // @RequestParam : query
    // @PathVariable : params (url: /user/{userId})
    public String helloMvn(@RequestParam("name") String name, Model model) {    //모델에 담으면 view에서 렌더링할때 사용
        model.addAttribute("name", name);
        return "hello-template";
    }

    /*
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello "+ name;
    }
     */

    @GetMapping("hello-api")
    @ResponseBody   //이걸 사용하고 객체를 반환하면 객체가 JSON으로 반환됨
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}