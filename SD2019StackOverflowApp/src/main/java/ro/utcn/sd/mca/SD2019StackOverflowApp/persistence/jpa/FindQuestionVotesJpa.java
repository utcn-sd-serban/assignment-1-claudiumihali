package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionVote;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.VoteType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class FindQuestionVotesJpa implements JpaSpecification<QuestionVote> {
    private Integer questionId;
    private VoteType voteType;

    FindQuestionVotesJpa(Integer questionId, VoteType voteType) {
        this.questionId = questionId;
        this.voteType = voteType;
    }

    @Override
    public CriteriaQuery<QuestionVote> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<QuestionVote> query = builder.createQuery(QuestionVote.class);
        Root<QuestionVote> root = query.from(QuestionVote.class);
        query.select(root);
        query.where(builder.equal(root.get("questionId"), questionId), builder.equal(root.get("voteType"), voteType.getDatabaseText()));
        return query;
    }
}
