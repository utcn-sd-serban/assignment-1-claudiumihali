package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class FindQuestionByTagIdJdbc implements JdbcSpecification<Question> {
    private Integer tagId;

    FindQuestionByTagIdJdbc(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT question.question_id, stack_overflow_user_id, title, text, creation_date_time FROM "
                + "question_tag JOIN question ON question.question_id=question_tag.question_id WHERE tag_id=?";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        List<Object> l = new ArrayList<>();
        l.add(tagId);
        return l;
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new Question(resultSet.getInt("question_id"),
                resultSet.getInt("stack_overflow_user_id"), resultSet.getString("title"),
                resultSet.getString("text"), resultSet.getObject("creation_date_time", LocalDateTime.class)));
    }
}
