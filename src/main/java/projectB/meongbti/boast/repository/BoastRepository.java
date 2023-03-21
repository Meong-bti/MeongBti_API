package projectB.meongbti.boast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projectB.meongbti.boast.entity.Boast;

import java.util.List;
import java.util.Optional;

public interface BoastRepository extends JpaRepository<Boast, Long> {

    @Query("select b from Boast b where b.member.memberId = :memberId")
    public List<Boast> findByMemberId(@Param("memberId") Long memberId);

}