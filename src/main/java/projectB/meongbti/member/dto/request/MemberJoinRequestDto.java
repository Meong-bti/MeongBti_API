package projectB.meongbti.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
public class MemberJoinRequestDto {
    private String memberEmail;
    private String memberPw;
    private String memberNick;


    @Builder
    public MemberJoinRequestDto(String memberEmail, String memberPw, String memberNick) {

        this.memberEmail = memberEmail;
        this.memberPw = memberPw;
        this.memberNick = memberNick;

    }






}
