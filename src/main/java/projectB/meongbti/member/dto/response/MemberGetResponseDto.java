package projectB.meongbti.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import projectB.meongbti.member.dto.MemberDto;

@Getter
@AllArgsConstructor
public class MemberGetResponseDto {

    private Long id;
    private String memberEmail;

    private String memberNick;



    public static MemberGetResponseDto fromMember(MemberDto memberDto){

        return  new MemberGetResponseDto(
                memberDto.getMemberId(),
                memberDto.getMemberEmail(),
                memberDto.getMemberNick()
        );

    }

}



