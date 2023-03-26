package projectB.meongbti.member.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import projectB.meongbti.pet.entity.Pet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE member SET deleted = true WHERE member_id=?") // 수정된 SQL 쿼리
@Where(clause = "deleted = false")

public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(unique = true, nullable = false)
    private String memberEmail;

    @Column(nullable = false)
    private String memberPw;

    @Column(nullable = false)
    private String memberNick;


    private boolean deleted;

    @OneToMany(mappedBy = "member")
    private List<Pet> petList = new ArrayList<>();


    @Builder(toBuilder = true)
    public Member(Long memberId,String memberEmail, String memberPw, String memberNick, boolean deleted) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberPw = memberPw;
        this.memberNick = memberNick;
        this.deleted = deleted;

    }



}
