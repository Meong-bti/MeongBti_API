package projectB.meongbti.util.image.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectB.meongbti.util.image.Entity.ImageMapping;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ImageRepository {

    private final EntityManager em;

    public void save(ImageMapping imageMapping) {
        em.persist(imageMapping);
    }

    public Optional<ImageMapping> findByStoreName(String storeName) {
        String jpql = "select i from ImageMapping i where i.imStore = :storeName";

        return em.createQuery(jpql, ImageMapping.class)
                .setParameter("storeName", storeName)
                .getResultList()
                .stream().findAny();
    }
}
