package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionTag;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class FindQuestionByTagIdJpa implements JpaSpecification<Question> {
    private Integer tagId;

    FindQuestionByTagIdJpa(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public CriteriaQuery<Question> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        Root<Question> rootQuestion = query.from(Question.class);
        Root<QuestionTag> rootQuestionTag = query.from(QuestionTag.class);
        query.select(rootQuestion);
        query.where(builder.equal(rootQuestion.get("questionId"), rootQuestionTag.get("questionId")),
                builder.equal(rootQuestionTag.get("tagId"), tagId));
        return query;
    }
}
