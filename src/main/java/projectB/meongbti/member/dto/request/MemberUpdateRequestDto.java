package projectB.meongbti.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdateRequestDto {


    private String memberPw;
    private String memberNick;
}
