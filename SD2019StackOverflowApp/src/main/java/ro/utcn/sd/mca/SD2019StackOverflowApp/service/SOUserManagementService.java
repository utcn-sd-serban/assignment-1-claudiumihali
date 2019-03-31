package ro.utcn.sd.mca.SD2019StackOverflowApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.SOUser;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.RepositoryFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.SpecificationFactory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SOUserManagementService {
    private final RepositoryFactory repositoryFactory;
    private final SpecificationFactory specificationFactory;

    @Transactional
    public Optional<SOUser> addUser(String username, String password) {
        Specification<SOUser> su = specificationFactory.createFindSOUserByUsername(username);
        List<SOUser> lu = repositoryFactory.createSOUserRepository().query(su);
        if (!lu.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(repositoryFactory.createSOUserRepository().save(new SOUser(null, username, password)));
        }
    }

    @Transactional
    public Optional<SOUser> verifyUserCredentials(String username, String password) {
        Specification<SOUser> su = specificationFactory.createVerifySOUserCredentials(username, password);
        List<SOUser> lu = repositoryFactory.createSOUserRepository().query(su);
        if (lu.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(lu.get(0));
        }
    }
}
