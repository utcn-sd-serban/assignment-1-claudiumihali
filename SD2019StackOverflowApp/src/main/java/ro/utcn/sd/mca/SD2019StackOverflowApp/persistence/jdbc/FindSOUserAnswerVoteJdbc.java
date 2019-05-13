package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.AnswerVote;

import java.util.ArrayList;
import java.util.List;

class FindSOUserAnswerVoteJdbc implements JdbcSpecification<AnswerVote> {
    private Integer sOUserId;
    private Integer answerId;

    FindSOUserAnswerVoteJdbc(Integer sOUserId, Integer answerId) {
        this.sOUserId = sOUserId;
        this.answerId = answerId;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT * FROM answer_vote WHERE stack_overflow_user_id=? AND answer_id=?";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        List<Object> l = new ArrayList<>();
        l.add(sOUserId);
        l.add(answerId);
        return l;
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new AnswerVote(resultSet.getInt("answer_vote_id"), resultSet.getInt("stack_overflow_user_id"),
                resultSet.getInt("answer_id"), resultSet.getString("vote_type")));
    }
}
