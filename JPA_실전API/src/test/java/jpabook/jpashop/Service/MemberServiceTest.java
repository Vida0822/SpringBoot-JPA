package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService ;
    @Autowired MemberRepository memberRepository ;
    @Autowired EntityManager en ;

    @Test
    public void 회원가입() throws Exception{

        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long saveId =  memberService.join(member) ;

        // then
        en.flush();
        Assert.assertEquals(member, memberRepository.findOne(saveId));
    } // 회원가입()

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{

        // given
        Member member1 = new Member() ;
        member1.setName("kim");

        Member member2 = new Member() ;
        member2.setName("kim");

        // when
        memberService.join(member1) ;
        memberService.join(member2) ;

        // then
        Assert.fail("예외가 발생 해야 한다.");
    } //  중복_회원_예외()

} // class