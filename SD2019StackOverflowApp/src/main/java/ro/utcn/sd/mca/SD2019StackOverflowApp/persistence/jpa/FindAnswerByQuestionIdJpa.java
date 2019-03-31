package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Answer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class FindAnswerByQuestionIdJpa implements JpaSpecification<Answer> {
    private Integer questionId;

    FindAnswerByQuestionIdJpa(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public CriteriaQuery<Answer> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<Answer> query = builder.createQuery(Answer.class);
        Root<Answer> root = query.from(Answer.class);
        query.select(root);
        query.where(builder.equal(root.get("questionId"), questionId));
        return query;
    }
}
