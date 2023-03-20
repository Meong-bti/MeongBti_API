package projectB.meongbti.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import projectB.meongbti.member.dto.request.MemberJoinRequestDto;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.member.service.MemberService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        String email = "test1";
        String password = "test1";
        String name = "test1";
        when(memberService.signup(email, password, name)).thenReturn(mock(Member.class));
        mockMvc.perform(post("/member/signup")
                .contentType("application/json")
                .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto(email, password, name))
        )).andDo(print())
                .andExpect(status().isOk());
    }
}