package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.DatabaseEntity;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

interface JpaSpecification<T extends DatabaseEntity> extends Specification<T> {
    CriteriaQuery<T> getJpaQuery(CriteriaBuilder builder);
}
