package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 회원을 저장하면 (Member save) 저장된 회원이 반환됨 (import ~)
    Member save (Member member);
    //id,name로 회원 찾기.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //저장된 모든 회원 리스트 검색.
    List<Member> findAll();

}
