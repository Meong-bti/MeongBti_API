package projectB.meongbti.pet.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectB.meongbti.pet.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    void savePet(Pet pet);

    void deletePet(Pet pet);

    public List<Pet> findBymemberId(Long memberId);

    public Optional<Pet> findOneByPetId(Long petId);

}
