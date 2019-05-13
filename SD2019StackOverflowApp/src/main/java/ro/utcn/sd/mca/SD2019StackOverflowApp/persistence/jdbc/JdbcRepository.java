package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.DatabaseEntity;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Repository;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
class JdbcRepository<T extends DatabaseEntity> implements Repository<T> {
    private final JdbcTemplate template;

    @Override
    public synchronized T save(T databaseEntity) {
        if (databaseEntity.getId() == null) {
            databaseEntity.setId(insert(databaseEntity));
        } else {
            update(databaseEntity);
        }
        return databaseEntity;
    }

    @Override
    public synchronized void remove(T databaseEntity) {
        StringBuilder sb = new StringBuilder();
        boolean firstFind = true;
        List<Object> argsValues = new ArrayList<>();
        if (databaseEntity.getId() != null) {
            firstFind = false;
            sb.append(databaseEntity.getAssociatedTableName()).append("_id").append("=?");
            argsValues.add(databaseEntity.getId());
        }
        for (Map.Entry entry : databaseEntity.getColumnFieldValueAssociations().entrySet()) {
            if (entry.getValue() != null) {
                if (firstFind) {
                    firstFind = false;
                    sb.append(entry.getKey()).append("=?");
                } else {
                    sb.append(" AND ").append(entry.getKey()).append("=?");
                }
                argsValues.add(entry.getValue());
            }
        }
        template.update("DELETE FROM " + databaseEntity.getAssociatedTableName() + " WHERE " + sb.toString(), argsValues.toArray());
    }

    @Override
    public synchronized List<T> query(Specification<T> specification) {
        JdbcSpecification<T> jdbcSpecification = (JdbcSpecification<T>) specification;
        return template.query(jdbcSpecification.getSqlCommand(), jdbcSpecification.getRowMapper(),
                jdbcSpecification.getSqlCommandArgsValues().toArray());
    }

    private int insert(T databaseEntity) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName(databaseEntity.getAssociatedTableName());
        insert.usingGeneratedKeyColumns(databaseEntity.getAssociatedTableName() + "_id");
        return insert.executeAndReturnKey(databaseEntity.getColumnFieldValueAssociations()).intValue();
    }

    private void update(T databaseEntity) {
        StringBuilder sb = new StringBuilder();
        boolean firstFind = true;
        List<Object> argsValues = new ArrayList<>();
        for (Map.Entry entry : databaseEntity.getColumnFieldValueAssociations().entrySet()) {
            if (entry.getValue() != null) {
                if (firstFind) {
                    firstFind = false;
                    sb.append(entry.getKey()).append("=?");
                } else {
                    sb.append(", ").append(entry.getKey()).append("=?");
                }
                argsValues.add(entry.getValue());
            }
        }
        argsValues.add(databaseEntity.getId());
        template.update("UPDATE " + databaseEntity.getAssociatedTableName() + " SET " + sb.toString() + " WHERE "
                + databaseEntity.getAssociatedTableName() + "_id=?", argsValues.toArray());
    }
}
