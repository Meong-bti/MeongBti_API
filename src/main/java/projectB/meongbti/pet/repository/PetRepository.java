package projectB.meongbti.pet.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projectB.meongbti.pet.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long>, QuerydslPetRepository {

    @Query("select p from Pet p where p.member.memberId = :memberId")
    public List<Pet> findByMemberId(@Param("memberId") Long memberId);

}
