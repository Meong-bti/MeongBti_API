package projectB.meongbti;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import projectB.meongbti.member.service.MemberService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final MemberService memberService;

    @PostConstruct
    public void initMemberData() {
        memberService.signup("test@test.com", "test", "testNick");
    }
}
