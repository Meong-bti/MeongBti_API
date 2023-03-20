package projectB.meongbti.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginRequestDto {

    private String memberEmail;
    private String memberPw;
}
