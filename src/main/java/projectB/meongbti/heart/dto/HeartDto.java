package projectB.meongbti.heart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projectB.meongbti.boast.entity.Boast;
import projectB.meongbti.heart.entity.Heart;
import projectB.meongbti.member.entity.Member;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeartDto {

    private Long heartId;

    private Member member;

    private Boast boast;
}
