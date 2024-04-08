package com.mysite.sbb.repository;

import com.mysite.sbb.dto.BoardDTO;
import com.mysite.sbb.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final SqlSessionTemplate sql;
    public MemberDTO findByMemberEmail(String memberEmail) {
        return sql.selectOne("Member.findByMemberEmail",memberEmail);
    }

}
