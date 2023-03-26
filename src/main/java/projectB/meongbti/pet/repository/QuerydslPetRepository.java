package projectB.meongbti.pet.repository;

import projectB.meongbti.pet.entity.Pet;

import java.util.List;

public interface QuerydslPetRepository {

    List<Pet> findPage();
}
