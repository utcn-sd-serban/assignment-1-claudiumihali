package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionTag;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Tag;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class FindQuestionTagsJpa implements JpaSpecification<Tag> {
    private Integer questionId;

    FindQuestionTagsJpa(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public CriteriaQuery<Tag> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        Root<Tag> rootTag = query.from(Tag.class);
        Root<QuestionTag> rootQuestionTag = query.from(QuestionTag.class);
        query.select(rootTag);
        query.where(builder.equal(rootTag.get("tagId"), rootQuestionTag.get("tagId")), builder.equal(rootQuestionTag.get("questionId"), questionId));
        return query;
    }
}
