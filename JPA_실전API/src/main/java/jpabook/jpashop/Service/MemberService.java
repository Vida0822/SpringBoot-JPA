package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository ;

    // 중복 회원 조회
    public void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName((member.getName())) ;
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다. ") ;
        }
    }  // validateDuplicateMember


    /*
    회원가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member) ;  // 중복 회원 검증 --> 예외
        memberRepository.save(member);
        return member.getId() ;
    } // join

    /*
    회원 조회
     */
   //  @Transactional(readOnly = true) // '읽기 전용 트랜젝션' : 리소스를 제한해 성능을 최적화
    public List<Member> findMembers(){
        return memberRepository.findAll() ;
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId) ;
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id) ;  // 영속성 컨텍스트에서 id로 찾고 없으면 db에서 끌어옴 (input , Read)
        member.setName(name) ; // 영속 상태 entity --> 트랜잭션 커밋 시점에 변경된 내용을 flush
    }
} // MemberService
