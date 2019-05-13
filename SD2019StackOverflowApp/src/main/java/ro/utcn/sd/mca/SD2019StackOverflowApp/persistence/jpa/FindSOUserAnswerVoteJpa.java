package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.AnswerVote;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

class FindSOUserAnswerVoteJpa implements JpaSpecification<AnswerVote> {
    private Integer sOUserId;
    private Integer answerId;

    FindSOUserAnswerVoteJpa(Integer sOUserId, Integer answerId) {
        this.sOUserId = sOUserId;
        this.answerId = answerId;
    }

    @Override
    public CriteriaQuery<AnswerVote> getJpaQuery(CriteriaBuilder builder) {
        CriteriaQuery<AnswerVote> query = builder.createQuery(AnswerVote.class);
        Root<AnswerVote> root = query.from(AnswerVote.class);
        query.select(root);
        query.where(builder.equal(root.get("sOUserId"), sOUserId), builder.equal(root.get("answerId"), answerId));
        return query;
    }
}
