package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Tag;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class FindTagByTextJpa implements JpaSpecification<Tag> {
    private String text;

    FindTagByTextJpa(String text) {
        this.text = text;
    }

    @Override
    public CriteriaQuery<Tag> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> root = query.from(Tag.class);
        query.select(root);
        query.where(builder.equal(root.get("text"), text));
        return query;
    }
}
