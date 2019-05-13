package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionVote;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class FindSOUserQuestionVoteJpa implements JpaSpecification<QuestionVote> {
    private Integer sOUserId;
    private Integer questionId;

    FindSOUserQuestionVoteJpa(Integer sOUserId, Integer questionId) {
        this.sOUserId = sOUserId;
        this.questionId = questionId;
    }

    @Override
    public CriteriaQuery<QuestionVote> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<QuestionVote> query = builder.createQuery(QuestionVote.class);
        Root<QuestionVote> root = query.from(QuestionVote.class);
        query.select(root);
        query.where(builder.equal(root.get("sOUserId"), sOUserId), builder.equal(root.get("questionId"), questionId));
        return query;
    }
}
