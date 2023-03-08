package projectB.meongbti.boast.entity;

import lombok.*;
import projectB.meongbti.member.entity.Member;
import projectB.meongbti.pet.entity.Pet;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Boast {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boastId;

    private LocalDateTime boastDate;

    private String boastContent;

    private String boastImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
