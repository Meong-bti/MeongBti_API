package projectB.meongbti.pet.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import projectB.meongbti.pet.entity.Pet;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class QuerydslPetRepositoryImpl implements QuerydslPetRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Pet> findPage() {
        return null;
    }
}
