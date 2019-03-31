package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.SOUser;

import java.util.ArrayList;
import java.util.List;

class FindSOUserByUsernameJdbc implements JdbcSpecification<SOUser> {
    private String username;

    FindSOUserByUsernameJdbc(String username) {
        this.username = username;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT * FROM stack_overflow_user WHERE username=?";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        List<Object> l = new ArrayList<>();
        l.add(username);
        return l;
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new SOUser(resultSet.getInt("stack_overflow_user_id"),
                resultSet.getString("username"), resultSet.getString("password")));
    }
}
