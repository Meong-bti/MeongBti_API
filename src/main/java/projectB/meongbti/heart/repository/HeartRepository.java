package projectB.meongbti.heart.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projectB.meongbti.heart.dto.HeartRequestDto;
import projectB.meongbti.heart.entity.Heart;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    @Query("select h from Heart h where h.member.memberId = :memberId and h.boast.boastId = :boastId")
    public Optional<Heart> findOneByMemberAndBoast(@Param("memberId") Long memberId, @Param("boastId") Long boastId);

    @EntityGraph(attributePaths = "boast")
    @Query("select h from Heart h where h.member.memberId = :memberId")
    public List<Heart> findByMemberId(@Param("memberId") Long memberId);

}
