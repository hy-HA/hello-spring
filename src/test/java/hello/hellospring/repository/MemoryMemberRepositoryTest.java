package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //각각 메서드가 끝날 때마다 동작하는 것.
    @AfterEach
    public void afterEach() {
        repository.clearStore();

    }

    //save()에 test를 import하면 save가 잘 동작하는지 테스트할 수 있음.
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //assrtThat > Assertions(org.assertj~)로 입력 후, option+엔터 해서 static으로 import해주기.
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    //findByName이 잘 동작하는지 테스트
   @Test
    public void findByName() {
        //member1을 spring1로 저장.
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //member2을 spring2로 저장.
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
