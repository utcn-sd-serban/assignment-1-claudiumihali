package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.AnswerVote;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.VoteType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class FindAnswerVotesJpa implements JpaSpecification<AnswerVote> {
    private Integer answerId;
    private VoteType voteType;

    FindAnswerVotesJpa(Integer answerId, VoteType voteType) {
        this.answerId = answerId;
        this.voteType = voteType;
    }

    @Override
    public CriteriaQuery<AnswerVote> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<AnswerVote> query = builder.createQuery(AnswerVote.class);
        Root<AnswerVote> root = query.from(AnswerVote.class);
        query.select(root);
        query.where(builder.equal(root.get("answerId"), answerId), builder.equal(root.get("voteType"), voteType.getDatabaseText()));
        return query;
    }
}
