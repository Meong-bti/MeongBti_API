package projectB.meongbti.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectB.meongbti.member.entity.Member;

import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberEmail(String memberEmail);
}
