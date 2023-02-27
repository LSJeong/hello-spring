package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemoryMemberRepository;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    //ctl+shift+T : 테스트 코드 껍데기 자동 생성
    //ctl+alt+v : 리턴타입,변수 자동생성
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /* 회원가입 */
    public long join(Member member){
        //같은 이름이 있는 중복 회원X
        /*Optional<Member> result = memberRepository.findByName(member.getName());  //null일 가능성이 있으면 optional로 감싸서 반환
        result.ifPresent(m ->{  //값이 있으면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        //위의 코드 압축해서 아래처럼 쓸 수 있음
        //Shift+Ctrl+Alt+T : 리팩토리 관련된 것들
        //Shift+Ctrl+Alt+T -> extract Method로 하나의 메소드로 뺴줌
        validateDuplicationMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicationMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
