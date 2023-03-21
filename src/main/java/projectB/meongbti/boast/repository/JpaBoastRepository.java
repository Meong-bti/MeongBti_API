package projectB.meongbti.boast.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectB.meongbti.boast.entity.Boast;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

//@Repository
@RequiredArgsConstructor
public class JpaBoastRepository {

    private final EntityManager em;

    /**
     * 자랑하기 등록
     */
    //@Override
    public void saveBoast(Boast boast) {
        em.persist(boast);
    }

    /**
     * 자랑하기 삭제
     */
    //@Override
    public void deleteBoast(Boast boast) {
        em.remove(boast);
    }

    /**
     * 자랑하기 조회
     */
    //@Override
    public Optional<Boast> findOneByBoastId(Long boastId) {
//        String jpql = "select b from Boast b where b.boastId = :boastId";
//
//        return em.createQuery(jpql, Boast.class)
//                .setParameter("boastId", boastId)
//                .getResultList()
//                .stream().findAny();

        return Optional.ofNullable(em.find(Boast.class, boastId));
    }

    //@Override
    public List<Boast> findByMemberId(Long memberId) {
        String jpql = "select b from Boast b where b.member.id = :memberId";

        return em.createQuery(jpql, Boast.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    //@Override
    public List<Boast> findAll() {
        String jpql = "select b from Boast b";

        return em.createQuery(jpql, Boast.class)
                .getResultList();
    }
}
