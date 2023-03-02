package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //PK설정, IDENTITY : oracle의 시퀀스 같은거
    private Long id;

    //@Column(name = "username") 테이블 칼럼명이 username일때 이렇게 써주면 맵핑됨
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
