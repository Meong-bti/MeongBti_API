package projectB.meongbti.heart.entity;

import lombok.*;
import projectB.meongbti.boast.entity.Boast;
import projectB.meongbti.heart.dto.HeartDto;
import projectB.meongbti.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Heart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boast_id")
    private Boast boast;

    //==Dto 변환 메서드==//
    public static HeartDto fromEntity(Heart heart) {
        return HeartDto.builder()
                .heartId(heart.getHeartId())
                .member(heart.getMember())
                .boast(heart.getBoast())
                .build();
    }
}
