package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.DatabaseEntity;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Repository;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RequiredArgsConstructor
class JpaRepository<T extends DatabaseEntity> implements Repository<T> {
    private final EntityManager entityManager;

    @Override
    public T save(T databaseEntity) {
        if (databaseEntity.getId() == null) {
            entityManager.persist(databaseEntity);
            return databaseEntity;
        } else {
            return entityManager.merge(databaseEntity);
        }
    }

    @Override
    public void remove(T databaseEntity) {
        entityManager.remove(databaseEntity);
    }

    @Override
    public List<T> query(Specification<T> specification) {
        JpaSpecification<T> jpaSpecification = (JpaSpecification<T>) specification;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        return entityManager.createQuery(jpaSpecification.getJpaQuery(builder)).getResultList();
    }
}
