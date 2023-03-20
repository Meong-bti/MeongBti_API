package projectB.meongbti.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import projectB.meongbti.member.entity.Member;

@Getter
@AllArgsConstructor
public class MemberJoinResponse {

    private Long id;
    private String memberEmail;
    private String memberNick;

    public static MemberJoinResponse fromMember(Member member){

        return  new MemberJoinResponse(
                member.getMemberId(),
                member.getMemberEmail(),
                member.getMemberNick()
        );



    }
}
