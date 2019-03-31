package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class FindQuestionByTitleJdbc implements JdbcSpecification<Question> {
    private String title;

    FindQuestionByTitleJdbc(String title) {
        this.title = title;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT * FROM question WHERE LOWER(title) LIKE LOWER('%" + title + "%')";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        return new ArrayList<>();
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new Question(resultSet.getInt("question_id"),
                resultSet.getInt("stack_overflow_user_id"), resultSet.getString("title"),
                resultSet.getString("text"), resultSet.getObject("creation_date_time", LocalDateTime.class)));
    }
}
