package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    //jpa는 EntityManager로 모든게 동작을 함.
    //build.gradle에서 다운받은 data-jpa라이브러와 properties에서의 설정,
    //데이터베이스 커넥션 정보랑 합쳐서
    //스프링부트는 자동으로 EntityManager를 생성해줌.
    //엔티티매니저는 내부적으로 데이터소스를 내부적으로 다 들고 있음. db와의 통신을 내부적으로 다 함.
    //현재 데이터베이스와 연결을 다 한 상태로. 개발자는 만들어진 엔티티매니저로 injection만 해주면 됨.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //List 변수와 리턴값이 같으면 컨트롤+t해서 inline으로 정리.
        //List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        //return result;
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
