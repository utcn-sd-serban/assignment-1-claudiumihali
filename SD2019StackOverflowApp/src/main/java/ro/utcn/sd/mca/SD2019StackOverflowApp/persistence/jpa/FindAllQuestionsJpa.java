package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class FindAllQuestionsJpa implements JpaSpecification<Question> {
    @Override
    public CriteriaQuery<Question> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);
        query.select(root);
        query.orderBy(builder.desc(root.get("creationDateTime")));
        return query;
    }
}
