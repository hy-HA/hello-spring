package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;

//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    //cmd+n>constructor로 인젝션 받기
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //빈 등록할 것이란 의미
    @Bean
    public MemberService memberService() {
        //멤버서비스는 리포지토리를 엮어줘야 함.
        return new MemberService(memberRepository());
    }

    //리포지토리도 빈으로 등록
//    @Bean
//    public MemberRepository memberRepository() {
    //return new MemoryMemberRepository();
    //return new JdbcMemberRepository(dataSource);
    //return new JdbcTemplateMemberRepository(dataSource);
    //return new JpaMemberRepository(em);
//    }

    //aop 스프링 빈에 등록
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
}
