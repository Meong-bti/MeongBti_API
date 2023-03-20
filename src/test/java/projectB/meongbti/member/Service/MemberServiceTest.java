package projectB.meongbti.member.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import projectB.meongbti.exception.member.ErrorCode;
import projectB.meongbti.exception.member.MemberException;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.repository.MemberRepository;
import projectB.meongbti.member.service.MemberService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    private String memberEmail;
    private String memberPw;
    private String memberNick;

    private Member testMember;
    @BeforeEach
    public void setup(){

         memberEmail = "test@test.com";
         memberPw = "test";
         memberNick = "test";


                testMember = Member.builder()
                .memberEmail(memberEmail)
                .memberPw(memberPw)
                .memberNick(memberNick)
                .build();

    }

    @Test
    public void 회원가입이_정상적으로_이루어지는지_테스트() {


        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(memberPw)).thenReturn("encrypt_password");
        when(memberRepository.save(any())).thenReturn(testMember);

        Assertions.assertDoesNotThrow(() -> memberService.signup(memberEmail, memberPw, memberNick));

    }
    @Test
    public void 회원가입에_이미_Email으로_있는지__테스트() {

        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.of(testMember));
        when(passwordEncoder.encode(memberPw)).thenReturn("encrypt_password");
        when(memberRepository.save(any())).thenReturn(Optional.of(testMember));

        MemberException e = Assertions.assertThrows(MemberException.class, () -> memberService.signup(memberEmail, memberPw, memberNick));
        Assertions.assertEquals(ErrorCode.DUPLICATED_MEMBER_EMAIL, e.getErrorCode());
    }

    @Test
    public void 로그인이_정상작동_되는_경우() {

        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.of(testMember));
        when(passwordEncoder.matches(memberPw, testMember.getMemberPw())).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> memberService.login(memberEmail, memberPw));
    }


    @Test
    public void 로그인시_Email로_회원가입한_유저가_없는경우() {

        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.empty());
        MemberException e = Assertions.assertThrows(MemberException.class, () -> memberService.login(memberEmail, memberPw));
        Assertions.assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());

    }


    @Test
    public void 로그인시_Email은_있지만_password가없는경우() {
        String wrongPw = "wrongPw";
        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.of(testMember));
        MemberException e = Assertions.assertThrows(MemberException.class, () -> memberService.login(memberEmail, wrongPw));
        Assertions.assertEquals(ErrorCode.INVALID_PASSWORD, e.getErrorCode());

    }


    @Test
    public void 회원수정이_정상작동_되는_경우() {

        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.of(testMember));
        when(passwordEncoder.encode(memberPw)).thenReturn("encrypt_password");
        when(memberRepository.save(any(Member.class))).thenReturn(testMember);
        Assertions.assertDoesNotThrow(() -> memberService.updateMember(memberEmail, memberPw, memberNick));
    }

    @Test
    public void 회원수정할때_유저가_존재하지않는_경우() {

        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.empty());
        MemberException e = Assertions.assertThrows(MemberException.class, () -> memberService.updateMember(memberEmail, memberPw, memberNick));
        Assertions.assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());
    }




    @Test
    public void 회원삭제() {

        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.of(testMember));
        when(memberRepository.save(any(Member.class))).thenReturn(testMember);
        Assertions.assertDoesNotThrow(() -> memberService.deleteMember(memberEmail));
    }


    @Test
    public void 회원삭제시_찾을수없는회원() {

        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.empty());
        MemberException e = Assertions.assertThrows(MemberException.class, () -> memberService.deleteMember(memberEmail));
        Assertions.assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());
    }

    @Test
    public void 회원삭제시_이미삭제된회원() {

        //mocking
        Member deletedMember = testMember.toBuilder().deleted(true).build();
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.of(deletedMember));
        MemberException e = Assertions.assertThrows(MemberException.class, () -> memberService.deleteMember(memberEmail));
        Assertions.assertEquals(ErrorCode.ALREADY_DELETED_USER, e.getErrorCode());
    }

    @Test
    public void 회원조회() {

        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.of(testMember));
        Assertions.assertDoesNotThrow(() -> memberService.findMember(memberEmail));
    }

    @Test
    public void 회원조회_찾을수없는회원() {

        //mocking
        when(memberRepository.findByMemberEmail(memberEmail)).thenReturn(Optional.empty());
        MemberException e = Assertions.assertThrows(MemberException.class, () -> memberService.findMember(memberEmail));
        Assertions.assertEquals(ErrorCode.USER_NOT_FOUND, e.getErrorCode());
    }



}
