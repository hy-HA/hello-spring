package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//jpa가 관리하는 엔티티
@Entity
public class Member {

    //pk매, db에 값을 넣으면 db가 알아서 id를 생성해주는 것을 Identity전략이라고 함.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //만약 db의 컬럼명이 name이 아니라 username이면,
    //@Column(name = "username")으로 어노테이션 걸어주면 됨.
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
