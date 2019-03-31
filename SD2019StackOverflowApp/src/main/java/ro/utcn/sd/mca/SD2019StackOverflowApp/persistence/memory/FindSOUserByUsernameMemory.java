package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.SOUser;

class FindSOUserByUsernameMemory implements MemorySpecification<SOUser> {
    private String username;

    FindSOUserByUsernameMemory(String username) {
        this.username = username;
    }

    @Override
    public boolean specified(SOUser soUser) {
        return username.equals(soUser.getUsername());
    }
}
