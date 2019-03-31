package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.SOUser;

class FindSOUserByIdMemory implements MemorySpecification<SOUser> {
    private Integer sOUserId;

    FindSOUserByIdMemory(Integer sOUserId) {
        this.sOUserId = sOUserId;
    }

    @Override
    public boolean specified(SOUser soUser) {
        return sOUserId.equals(soUser.getId());
    }
}
