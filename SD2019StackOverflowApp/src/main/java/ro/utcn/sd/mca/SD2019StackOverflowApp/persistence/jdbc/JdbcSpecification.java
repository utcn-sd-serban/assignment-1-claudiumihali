package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.DatabaseEntity;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;

import java.util.List;

interface JdbcSpecification<T extends DatabaseEntity> extends Specification<T> {
    String getSqlCommand();
    List<Object> getSqlCommandArgsValues();
    RowMapper getRowMapper();
}
