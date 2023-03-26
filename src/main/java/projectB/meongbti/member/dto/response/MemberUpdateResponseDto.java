package projectB.meongbti.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import projectB.meongbti.member.dto.MemberDto;
@Getter
@AllArgsConstructor
public class MemberUpdateResponseDto {

    private Long id;
    private String memberNick;
    public static MemberUpdateResponseDto fromMember(MemberDto memberDto){

        return  new MemberUpdateResponseDto(
                memberDto.getMemberId(),
                memberDto.getMemberNick()
        );

    }

}



