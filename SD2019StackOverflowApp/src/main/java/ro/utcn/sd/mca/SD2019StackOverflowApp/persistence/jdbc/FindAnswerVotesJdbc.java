package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.AnswerVote;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.VoteType;

import java.util.ArrayList;
import java.util.List;

class FindAnswerVotesJdbc implements JdbcSpecification<AnswerVote> {
    private Integer answerId;
    private VoteType voteType;

    FindAnswerVotesJdbc(Integer answerId, VoteType voteType) {
        this.answerId = answerId;
        this.voteType = voteType;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT * FROM answer_vote WHERE answer_id=? AND vote_type=?";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        List<Object> l = new ArrayList<>();
        l.add(answerId);
        l.add(voteType.getDatabaseText());
        return l;
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new AnswerVote(resultSet.getInt("answer_vote_id"),
                resultSet.getInt("stack_overflow_user_id"), resultSet.getInt("answer_id"),
                resultSet.getString("vote_type")));
    }
}
