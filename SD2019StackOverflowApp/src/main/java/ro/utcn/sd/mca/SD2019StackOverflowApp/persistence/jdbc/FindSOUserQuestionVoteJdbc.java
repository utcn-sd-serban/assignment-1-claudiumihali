package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionVote;

import java.util.ArrayList;
import java.util.List;

class FindSOUserQuestionVoteJdbc implements JdbcSpecification<QuestionVote> {
    private Integer sOUserId;
    private Integer questionId;

    FindSOUserQuestionVoteJdbc(Integer sOUserId, Integer questionId) {
        this.sOUserId = sOUserId;
        this.questionId = questionId;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT * FROM question_vote WHERE stack_overflow_user_id=? AND question_id=?";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        List<Object> l = new ArrayList<>();
        l.add(sOUserId);
        l.add(questionId);
        return l;
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new QuestionVote(resultSet.getInt("question_vote_id"), resultSet.getInt("stack_overflow_user_id"),
                resultSet.getInt("question_id"), resultSet.getString("vote_type")));
    }
}
