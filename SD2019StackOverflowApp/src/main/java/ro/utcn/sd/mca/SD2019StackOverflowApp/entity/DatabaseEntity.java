package ro.utcn.sd.mca.SD2019StackOverflowApp.entity;

import java.util.Map;

public interface DatabaseEntity {
    Integer getId();
    void setId(Integer id);
    String getAssociatedTableName();
    Map<String, Object> getColumnFieldValueAssociations();
    void updateEntityFields(DatabaseEntity entity);
}
