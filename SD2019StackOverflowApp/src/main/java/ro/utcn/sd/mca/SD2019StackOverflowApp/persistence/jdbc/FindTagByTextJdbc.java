package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Tag;

import java.util.ArrayList;
import java.util.List;

class FindTagByTextJdbc implements JdbcSpecification<Tag> {
    private String text;

    FindTagByTextJdbc(String text) {
        this.text = text;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT * FROM tag WHERE text=?";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        List<Object> l = new ArrayList<>();
        l.add(text);
        return l;
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new Tag(resultSet.getInt("tag_id"), resultSet.getString("text")));
    }
}
