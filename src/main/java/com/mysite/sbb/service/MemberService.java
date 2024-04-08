package com.mysite.sbb.service;

import com.mysite.sbb.dto.MemberDTO;
import com.mysite.sbb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
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

}
