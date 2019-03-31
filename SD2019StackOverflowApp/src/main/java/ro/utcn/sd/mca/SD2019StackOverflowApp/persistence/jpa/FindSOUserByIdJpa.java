package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.SOUser;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class FindSOUserByIdJpa implements JpaSpecification<SOUser> {
    private Integer sOUserId;

    FindSOUserByIdJpa(Integer sOUserId) {
        this.sOUserId = sOUserId;
    }

    @Override
    public CriteriaQuery<SOUser> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<SOUser> query = builder.createQuery(SOUser.class);
        Root<SOUser> root = query.from(SOUser.class);
        query.select(root);
        query.where(builder.equal(root.get("sOUserId"), sOUserId));
        return query;
    }
}
