package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.DatabaseEntity;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Repository;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MemoryRepository<T extends DatabaseEntity> implements Repository<T> {
    private final Map<Integer, T> data = new HashMap<>();
    private Integer currentId = 1;

    @Override
    public synchronized T save(T databaseEntity) {
        if (databaseEntity.getId() == null) {
            databaseEntity.setId(currentId++);
            data.put(databaseEntity.getId(), databaseEntity);
        } else {
            T entity = data.get(databaseEntity.getId());
            if (entity != null) {
                entity.updateEntityFields(databaseEntity);
            } else {
                data.put(databaseEntity.getId(), databaseEntity);
            }
        }
        return databaseEntity;
    }

    @Override
    public synchronized void remove(T databaseEntity) {
        data.remove(databaseEntity.getId());
    }

    @Override
    public synchronized List<T> query(Specification<T> specification) {
        MemorySpecification<T> memorySpecification = (MemorySpecification<T>) specification;
        List<T> tList = new ArrayList<>();
        for (T t : data.values()) {
            if (memorySpecification.specified(t))
                tList.add(t);
        }
        if (memorySpecification instanceof FindAllQuestionsMemory)
            tList.sort((t1, t2) -> {
                Question q1 = (Question) t1;
                Question q2 = (Question) t2;
                return q2.getCreationDateTime().compareTo(q1.getCreationDateTime());
            });
        return tList;
    }
}
