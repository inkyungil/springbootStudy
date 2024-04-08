package com.mysite.sbb.controller;

import com.mysite.sbb.dto.MemberDTO;
import com.mysite.sbb.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    public String save(@ModelAttribute MemberDTO memberDTO)  {
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



    @GetMapping("/member/")
    public String member_main() {
        return "/member/index";
    }


    @GetMapping("/member/list")
    public String member_list(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList" , memberDTOList);
        System.out.println("memberDTOList = " + memberDTOList);

        return "/member/list";
    }
    @GetMapping("/member/{id}")
    public String findById(Model model, @PathVariable("id") Long id){

        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member" , memberDTO);
        return "/member/detail";
    }

    @GetMapping("/member/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        memberService.delete(id);
        return "redirect:/member/list";
    }



    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){

        String checkRequest = memberService.emailCheck(memberEmail);
        if(checkRequest != null) {
            return "ok";
        }else{
            return "no";
        }
    }


}
