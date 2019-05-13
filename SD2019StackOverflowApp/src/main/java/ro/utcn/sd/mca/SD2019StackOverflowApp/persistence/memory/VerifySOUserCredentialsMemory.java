package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.SOUser;

class VerifySOUserCredentialsMemory implements MemorySpecification<SOUser> {
    private String username;
    private String password;

    VerifySOUserCredentialsMemory(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean specified(SOUser soUser) {
        return username.equals(soUser.getUsername()) && password.equals(soUser.getPassword());
    }
}
