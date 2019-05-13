package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.DatabaseEntity;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;

interface MemorySpecification<T extends DatabaseEntity> extends Specification<T> {
    boolean specified(T t);
}
