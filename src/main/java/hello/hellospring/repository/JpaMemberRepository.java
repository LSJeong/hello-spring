package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;  //spring boot가 알아서 생성

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
        Member member = em.find(Member.class, id);  //PK일 경우
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //PK 아닐경우
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        //return result;
        //return 같으면 ctrl+alt+n하면 inline, 한줄로 줄일 수 있음
        return em.createQuery("select m from Member m", Member.class).getResultList();

    }
}
