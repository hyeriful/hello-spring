package com.hello.hellospring.controller;

//createMemberForm.html input tag에서 name="name"에 들어간 값이
//이 클래스 name으로 매핑. setName을 통해 저장됨.
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
