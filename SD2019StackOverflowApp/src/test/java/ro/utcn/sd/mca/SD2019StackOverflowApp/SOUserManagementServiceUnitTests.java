package ro.utcn.sd.mca.SD2019StackOverflowApp;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.SOUser;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.RepositoryFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.SpecificationFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory.MemoryRepositoryFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory.MemorySpecificationFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.SOUserManagementService;

import java.util.Optional;

public class SOUserManagementServiceUnitTests {
    private static RepositoryFactory createMockedRepositoryFactory() {
        RepositoryFactory factory = new MemoryRepositoryFactory();

        factory.createSOUserRepository().save(new SOUser(null, "u1", "u1"));

        return factory;
    }

    private static SpecificationFactory createMockedSpecificationFactory() {
        return new MemorySpecificationFactory();
    }

    @Test
    public void testLogIn() {
        RepositoryFactory repositoryFactory = createMockedRepositoryFactory();
        SpecificationFactory specificationFactory = createMockedSpecificationFactory();
        SOUserManagementService sOUserService = new SOUserManagementService(repositoryFactory, specificationFactory);

        Optional<SOUser> userOptional = sOUserService.verifyUserCredentials("u1", "u1");

        Assert.assertTrue(userOptional.isPresent());
        Assert.assertEquals(new SOUser(1, "u1", "u1"), userOptional.get());
    }

    @Test
    public void testLogInInvalidCredentials() {
        RepositoryFactory repositoryFactory = createMockedRepositoryFactory();
        SpecificationFactory specificationFactory = createMockedSpecificationFactory();
        SOUserManagementService sOUserService = new SOUserManagementService(repositoryFactory, specificationFactory);

        Optional<SOUser> userOptional = sOUserService.verifyUserCredentials("u1", "u2");

        Assert.assertFalse(userOptional.isPresent());
    }
}
