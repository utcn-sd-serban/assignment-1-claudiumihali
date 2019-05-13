package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.SOUser;

import java.util.ArrayList;
import java.util.List;

class FindSOUserByIdJdbc implements JdbcSpecification<SOUser> {
    private Integer sOUserId;

    FindSOUserByIdJdbc(Integer sOUserId) {
        this.sOUserId = sOUserId;
    }

    @Override
    public String getSqlCommand() {
        return "SELECT * FROM stack_overflow_user WHERE stack_overflow_user_id=?";
    }

    @Override
    public List<Object> getSqlCommandArgsValues() {
        List<Object> l = new ArrayList<>();
        l.add(sOUserId);
        return l;
    }

    @Override
    public RowMapper getRowMapper() {
        return ((resultSet, i) -> new SOUser(resultSet.getInt("stack_overflow_user_id"),
                resultSet.getString("username"), resultSet.getString("password")));
    }
}
