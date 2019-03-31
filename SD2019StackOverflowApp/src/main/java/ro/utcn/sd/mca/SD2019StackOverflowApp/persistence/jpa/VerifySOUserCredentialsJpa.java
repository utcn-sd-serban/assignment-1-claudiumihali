package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.SOUser;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class VerifySOUserCredentialsJpa implements JpaSpecification<SOUser> {
    private String username;
    private String password;

    VerifySOUserCredentialsJpa(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public CriteriaQuery<SOUser> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<SOUser> query = builder.createQuery(SOUser.class);
        Root<SOUser> root = query.from(SOUser.class);
        query.select(root);
        query.where(builder.equal(root.get("username"), username), builder.equal(root.get("password"), password));
        return query;
    }
}
