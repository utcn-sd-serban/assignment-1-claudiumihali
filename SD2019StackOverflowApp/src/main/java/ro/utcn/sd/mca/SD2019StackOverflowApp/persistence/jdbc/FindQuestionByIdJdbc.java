package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class FindQuestionByIdJdbc implements JdbcSpecification<Question> {
    private Integer questionId;

    FindQuestionByIdJdbc(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT * FROM question WHERE question_id=?";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        List<Object> l = new ArrayList<>();
        l.add(questionId);
        return l;
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new Question(resultSet.getInt("question_id"),
                resultSet.getInt("stack_overflow_user_id"), resultSet.getString("title"),
                resultSet.getString("text"), resultSet.getObject("creation_date_time", LocalDateTime.class)));
    }
}
