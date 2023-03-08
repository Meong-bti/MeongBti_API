package projectB.meongbti.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import projectB.meongbti.member.entity.Member;

@AllArgsConstructor
@Getter
public class MemberDto {
    private String memberEmail;
    private String memberPw;
    private String memberNick;


    public static Member fromEntity(Member member) {
        return new Member(
            member.getMemberEmail(),
            member.getMemberPw(),
            member.getMemberNick()
        );
    }
}



