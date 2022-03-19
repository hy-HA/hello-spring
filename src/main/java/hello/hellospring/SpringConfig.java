package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //빈 등록할 것이란 의미
    @Bean
    public MemberService memberService() {
        //멤버서비스는 리포지토리를 엮어줘야 함.
        return new MemberService(memberRepository());
    }

    //리포지토리도 빈으로 등록
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
