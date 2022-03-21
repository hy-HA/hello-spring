package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    //스프링에 등록되어있는 구현체가 configration한 곳에서 올라올 것
    @Autowired MemberService memberService;
    @Autowired MemoryMemberRepository memberRepository;

    //@BeforeEach
    //public void beforeEach() {
    //    memberRepository = new MemoryMemberRepository();
    //    memberService = new MemberService(memberRepository);
    //}

    //@AfterEach
    //public void afterEach() {
    //    memberRepository.clearStore();
    //}

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        //memberService.join(member)상태에서 옵션+커맨드+v 후 savedId입력.
        Long saveId = memberService.join(member);

        //then
        //memberService.findOne(saveId);상태에서 옵션+커맨드+v
        //get으로 받고 다시 옵션+커맨드+v
        Member findMember = memberService.findOne(saveId).get();
        //Assertions JUnit이 아닌 asserJ로
        //Assertions.assertThat(member.getName())isEqual(findMember.getName());
        //상태에서 Assertions를 옵션 + 엔터로 static import처리.
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //1. 중복가입시 오류뜨는지 확인
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //상태에서 asserthrows에서 커맨드+옵션+v
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //2. 메세지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //then
    }

}