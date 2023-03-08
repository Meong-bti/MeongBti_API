package projectB.meongbti.pet.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectB.meongbti.pet.entity.Pet;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaPetRepository implements PetRepository {

    private final EntityManager em;

    /**
     * 펫 등록
     */
    @Override
    public void savePet(Pet pet) {
        em.persist(pet);
    }

    /**
     * 펫 삭제
     */
    @Override
    public void deletePet(Pet pet) {
        em.remove(pet);
    }

    /**
     * 멤버ID를 이용하여 멤버의 애완동물을 조회
     */
    @Override
    public List<Pet> findBymemberId(Long memberId) {
        String jpql = "select p from Pet p where p.member.id = :memberId";

        List<Pet> findPets = em.createQuery(jpql, Pet.class)
                .setParameter("memberId", memberId)
                .getResultList();

        return findPets;
    }

    /**
     * 펫ID를 이용하여 펫 정보 조회
     */
    @Override
    public Optional<Pet> findOneByPetId(Long petId) {
//        String jpql = "select p from Pet p where p.petId = :petId";
//
//        return em.createQuery(jpql, Pet.class)
//                .setParameter("petId", petId)
//                .getResultList()
//                .stream().findAny();

        return Optional.ofNullable(em.find(Pet.class, petId));
    }
}
