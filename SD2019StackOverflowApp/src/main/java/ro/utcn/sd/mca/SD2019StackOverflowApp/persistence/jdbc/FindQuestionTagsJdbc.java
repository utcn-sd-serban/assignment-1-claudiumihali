package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Tag;

import java.util.ArrayList;
import java.util.List;

class FindQuestionTagsJdbc implements JdbcSpecification<Tag> {
    private Integer questionId;

    FindQuestionTagsJdbc(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT tag.tag_id, text FROM question_tag JOIN tag ON tag.tag_id=question_tag.tag_id WHERE question_id=?";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        List<Object> l = new ArrayList<>();
        l.add(questionId);
        return l;
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new Tag(resultSet.getInt("tag_id"), resultSet.getString("text")));
    }
}
