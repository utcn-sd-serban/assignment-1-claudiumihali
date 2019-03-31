package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionVote;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.VoteType;

import java.util.ArrayList;
import java.util.List;

class FindQuestionVotesJdbc implements JdbcSpecification<QuestionVote> {
    private Integer questionId;
    private VoteType voteType;

    FindQuestionVotesJdbc(Integer questionId, VoteType voteType) {
        this.questionId = questionId;
        this.voteType = voteType;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT * FROM question_vote WHERE question_id=? AND vote_type=?";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        List<Object> l = new ArrayList<>();
        l.add(questionId);
        l.add(voteType.getDatabaseText());
        return l;
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new QuestionVote(resultSet.getInt("question_vote_id"),
                resultSet.getInt("stack_overflow_user_id"), resultSet.getInt("question_id"),
                resultSet.getString("vote_type")));
    }
}
