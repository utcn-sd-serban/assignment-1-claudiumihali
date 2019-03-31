package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.DatabaseEntity;

import java.util.List;

public interface Repository<T extends DatabaseEntity> {
    T save(T databaseEntity);
    void remove(T databaseEntity);
    List<T> query(Specification<T> specification);
}
