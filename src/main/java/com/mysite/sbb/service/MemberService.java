package com.mysite.sbb.service;

import com.mysite.sbb.dto.MemberDTO;
import com.mysite.sbb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO){
        System.out.println("memberDTO = " + memberDTO);
        memberRepository.save(memberDTO);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberDTO> byMemberEmail = Optional.ofNullable(memberRepository.findByMemberEmail(memberDTO.getMemberEmail()));
        System.out.println("byMemberEmail = " + byMemberEmail);
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            if (byMemberEmail.get().getMemberPassword().equals(memberDTO.getMemberPassword())) {
                System.out.println("로그인 성공");
                return memberDTO;
            } else {

                System.out.println("byMemberEmail.get() = " + byMemberEmail.get());
                System.out.println("memberDTO.getMemberPassword() = " + memberDTO.getMemberPassword());
                // 비밀번호 불일치(로그인실패)
                System.out.println("비밀번호 불일치(로그인실패)");
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
            System.out.println("조회 결과가 없다(해당 이메일을 가진 회원이 없다)");
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        return memberRepository.findAll();
    }

    public MemberDTO findById(Long id) {
        return memberRepository.finById(id);
    }


    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public String emailCheck(String memberEmail){

        Optional<MemberDTO> byMemberEmail = Optional.ofNullable(memberRepository.findByMemberEmail(memberEmail));
        System.out.println("memberRepository.findByMemberEmail(memberEmail) = " + memberRepository.findByMemberEmail(memberEmail));
        if(byMemberEmail.isPresent()){
            //조회결과가 있다.
            return null;
        }else {
            //조회결과 없다.
            return "ok";
        }
    }
}
