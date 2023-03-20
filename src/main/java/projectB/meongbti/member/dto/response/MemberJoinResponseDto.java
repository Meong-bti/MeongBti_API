package projectB.meongbti.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import projectB.meongbti.member.dto.MemberDto;
import projectB.meongbti.member.entity.Member;

@Getter
@AllArgsConstructor
public class MemberJoinResponseDto {

    private Long id;
    private String memberEmail;
    private String memberNick;

    public static MemberJoinResponseDto fromMember(MemberDto memberDto){

        return  new MemberJoinResponseDto(
                memberDto.getMemberId(),
                memberDto.getMemberEmail(),
                memberDto.getMemberNick()
        );



    }
}
