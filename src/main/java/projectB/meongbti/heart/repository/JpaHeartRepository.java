package projectB.meongbti.heart.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectB.meongbti.heart.dto.HeartRequestDto;
import projectB.meongbti.heart.entity.Heart;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

//@Repository
@RequiredArgsConstructor
public class JpaHeartRepository {

    private final EntityManager em;

    /**
     * 좋아요 추가
     */
    //@Override
    public void addHeart(Heart heart) {
        em.persist(heart);
    }

    /**
     * 좋아요 삭제
     */
    //@Override
    public void cancelHeart(Heart heart) {
        em.remove(heart);
    }

    /**
     * 좋아요 정보 조회 By MemberId And BoastId
     */
    //@Override
    public Optional<Heart> findOneByMemberAndBoast(HeartRequestDto heartRequestDto) {
        String jpql = "select h from Heart h where h.member.memberId = :memberId and h.boast.boastId = :boastId";

        return em.createQuery(jpql, Heart.class)
                .setParameter("memberId", heartRequestDto.getMemberId())
                .setParameter("boastId", heartRequestDto.getBoastId())
                .getResultList()
                .stream().findAny();
    }

    /**
     * 멤버ID를 이용하여 좋아요 한 자랑하기 목록 조회
     */
    //@Override
    public List<Heart> findByMemberId(Long memberId) {
        String jpql = "select h from Heart h " +
                "join fetch Boast b " +
                "join fetch Member m " +
                "where h.member.memberId = :memberId";

        return em.createQuery(jpql, Heart.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
