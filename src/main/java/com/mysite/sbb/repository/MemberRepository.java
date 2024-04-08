package com.mysite.sbb.repository;

import com.mysite.sbb.dto.BoardDTO;
import com.mysite.sbb.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final SqlSessionTemplate sql;

    public MemberDTO save(MemberDTO memberDTO) {
         sql.insert("Member.save", memberDTO);
         return memberDTO;
    }
    public MemberDTO findByMemberEmail(String memberEmail) {
        System.out.println("memberEmail111 = " + memberEmail);
        return sql.selectOne("Member.findByMemberEmail",memberEmail);
    }

    public List<MemberDTO> findAll() {
        return sql.selectList("Member.findAll");
    }

    public MemberDTO finById(Long id) {
        return sql.selectOne("Member.findById", id);
    }

    public void delete(Long id) {
        sql.delete("Member.delete", id);
    }
}
