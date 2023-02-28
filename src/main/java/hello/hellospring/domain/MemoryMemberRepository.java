package hello.hellospring.domain;

import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
//1. 컴포넌트스캔과 자동의존관계 설정
//@Repository
//2. 자바 코드로 직접 스프링빈 등록하기 -> SpringConfig 파일 생성
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();  //비우기
    }
}
