package projectB.meongbti.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import projectB.meongbti.exception.member.ErrorCode;
import projectB.meongbti.exception.member.MemberException;
import projectB.meongbti.member.dto.MemberDto;
import projectB.meongbti.member.dto.request.MemberJoinRequestDto;
import projectB.meongbti.member.dto.request.MemberLoginRequestDto;
import projectB.meongbti.member.dto.request.MemberUpdateRequestDto;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.service.MemberService;

import javax.persistence.PreUpdate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockAuthentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberService memberService;
    private UsernamePasswordAuthenticationToken mockAuthentication;

@BeforeEach
    public void setupMockAuthentication() {
        // 가짜 사용자 정보를 설정합니다.
        String memberEmail = "test@example.com";
        String memberPw = "test1";
        String memberNick = "test1";

        // 사용자 권한을 설정합니다. 필요한 경우 여러 권한을 추가할 수 있습니다.
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 가짜 회원 객체를 생성합니다.
        Member member = Member.builder()
                .memberEmail(memberEmail)
                .memberPw(memberPw)
                .memberNick(memberNick)
                .build();

        MemberDto memberDto = MemberDto.fromEntity(member);


        // 가짜 Authentication 객체를 생성하고 인스턴스 변수에 저장합니다.
        this.mockAuthentication = new UsernamePasswordAuthenticationToken(memberDto, null, authorities);
    }
    @Test
    public void 회원가입() throws Exception {
        String email = "test1";
        String password = "test1";
        String name = "test1";
        when(memberService.signup(email, password, name)).thenReturn(mock(MemberDto.class));
        mockMvc.perform(post("/member/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto(email, password, name))
        )).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입_중복() throws Exception{

        String memberEmail = "test1";
        String memberPw = "test1";
        String memberNick = "test1";
        when(memberService.signup(memberEmail, memberPw, memberNick)).thenThrow(new MemberException(ErrorCode.DUPLICATED_MEMBER_EMAIL));
        mockMvc.perform(post("/member/signup")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto(memberEmail, memberPw, memberNick))
                        )).andDo(print())
                .andExpect(status().isConflict());



    }


    @Test
    public void 로그인() throws Exception{

        String memberEmail = "test1";
        String memberPw = "test1";
        when(memberService.login(memberEmail, memberPw)).thenReturn("token");
        mockMvc.perform(post("/member/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto(memberEmail, memberPw))
                        )).andDo(print())
                .andExpect(status().isOk());



    }


    @Test
    public void 로그인시_회원가입안된_Email작성() throws Exception  {
        String memberEmail = "test1";
        String memberPw = "test1";
        when(memberService.login(memberEmail, memberPw)).thenThrow(new MemberException(ErrorCode.USER_NOT_FOUND));
        mockMvc.perform(post("/member/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto(memberEmail, memberPw))
                        )).andDo(print())
                .andExpect(status().isNotFound());

    }


    @Test
    public void 로그인시_틀린_PASSWORD_입력() throws Exception  {
        String memberEmail = "test1";
        String memberPw = "test1";
        when(memberService.login(memberEmail, memberPw)).thenThrow(new MemberException(ErrorCode.INVALID_PASSWORD));
        mockMvc.perform(post("/member/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto(memberEmail, memberPw))
                        )).andDo(print())
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void 회원수정() throws Exception{
        String memberEmail = "test@example.com";
        String memberPw = "test123";
        String memberNick = "test123";
        when(memberService.updateMember(memberEmail,memberPw,memberNick)).thenReturn(mock(MemberDto.class));
        mockMvc.perform(put("/member/update")
                        .with(authentication(mockAuthentication))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(new MemberUpdateRequestDto(memberPw, memberNick))
                        )).andDo(print())
                .andExpect(status().isOk());



    }

    @Test
    public void 회원수정시_존재하지_않는_회원() throws Exception{
        String memberEmail = "test@example.com";
        String memberPw = "test123";
        String memberNick = "test123";
        when(memberService.updateMember(memberEmail,memberPw,memberNick)).thenThrow(new MemberException(ErrorCode.USER_NOT_FOUND));
        mockMvc.perform(put("/member/update")
                        .with(authentication(mockAuthentication))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(new MemberUpdateRequestDto(memberPw, memberNick))
                        )).andDo(print())
                .andExpect(status().isNotFound());



    }



    @Test
    public void 회원삭제() throws Exception {
        String memberEmail = "test@example.com";

        // 회원 삭제 메서드가 호출되면 아무것도 하지 않도록 설정
        mockMvc.perform(delete("/member/delete")
                        .with(authentication(mockAuthentication)))
                .andDo(print())
                .andExpect(status().isOk());

    }



    @Test
    public void 회원삭제시_존재하지_않는_회원() throws Exception {
        String memberEmail = "test@example.com";

        // 회원 삭제 메서드가 호출되면 MemberException을 발생시킴
        doThrow(new MemberException(ErrorCode.USER_NOT_FOUND))
                .when(memberService).deleteMember(memberEmail);

        mockMvc.perform(delete("/member/delete")
                        .with(authentication(mockAuthentication)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }




    @Test
    public void 회원조회() throws Exception {
        String memberEmail = "test@example.com";
        when(memberService.findMember(memberEmail)).thenReturn(mock(MemberDto.class));
        // 회원 삭제 메서드가 호출되면 아무것도 하지 않도록 설정
        mockMvc.perform(get("/member/find")
                        .with(authentication(mockAuthentication)))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    public void 회원조회시_존재하지_않는_회원() throws Exception {
        String memberEmail = "test@example.com";

        when(memberService.findMember(memberEmail)).thenThrow(new MemberException(ErrorCode.USER_NOT_FOUND));

        mockMvc.perform(get("/member/find")
                        .with(authentication(mockAuthentication)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }



}