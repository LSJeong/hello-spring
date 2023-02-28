package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemoryMemberRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//1. 컴포넌트스캔과 자동의존관계 설정
//@Service
//2. 자바 코드로 직접 스프링빈 등록하기 -> SpringConfig 파일 생성
public class MemberService {
    //ctl+shift+T : 테스트 코드 껍데기 자동 생성
    //ctl+alt+v : 리턴타입,변수 자동생성
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //테스트 코드에서 같은 인스턴스로 테스트 하기위해 위 소스 수정
    private final MemberRepository memberRepository;

    //DI 라고 함
    //직접스프링빈 등록하기때문에 주석처리 @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }  //생성자 주입

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
