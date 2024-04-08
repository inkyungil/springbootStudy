package com.mysite.sbb.controller;

import com.mysite.sbb.dto.MemberDTO;
import com.mysite.sbb.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "/member/save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "/member/login";
    }

    @GetMapping("/member/login")
    public String loingForm(){
        return "/member/login";
    }

    @PostMapping("/member/login")
    public String  login(MemberDTO memberDTO, HttpSession session){
        System.out.println("memberDTO = " + memberDTO + ", session = " + session);


        MemberDTO loginResult = memberService.login(memberDTO);
        System.out.println("loginResult = " + loginResult);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "/member/main";
        } else {
            // login 실패
            return "/member/login";
        }
    }


    @GetMapping("/member/main")
    public String memberMain(){
        return "/member/main";
    }


    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

}
