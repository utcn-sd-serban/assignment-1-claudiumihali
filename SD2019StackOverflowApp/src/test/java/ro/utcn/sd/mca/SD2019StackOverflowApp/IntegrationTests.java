package ro.utcn.sd.mca.SD2019StackOverflowApp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionVote;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.SOUser;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.VoteType;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.RepositoryFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.QuestionManagementService;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.SOUserManagementService;

import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IntegrationTests {

    // NU MERGE :(

    @Autowired
    private RepositoryFactory factory;
    @Autowired
    private SOUserManagementService sOUserService;
    @Autowired
    private QuestionManagementService questionService;

    @Before
    public void seed() {
        factory.createSOUserRepository().save(new SOUser(1, "u1", "u1"));
        factory.createSOUserRepository().save(new SOUser(2, "u2", "u2"));
        factory.createSOUserRepository().save(new SOUser(3, "u3", "u3"));

        factory.createQuestionRepository().save(new Question(1, 1, "q1", "q1", LocalDateTime.now()));
        factory.createQuestionRepository().save(new Question(2, 2, "q2", "q2", LocalDateTime.now()));
        factory.createQuestionRepository().save(new Question(3, 2, "q3", "q3", LocalDateTime.now()));

        factory.createQuestionVoteRepository().save(new QuestionVote(1, 2, 1, VoteType.DOWNVOTE.getDatabaseText()));
    }

    @Test
    public void testLogIn() {
        Optional<SOUser> userOptional = sOUserService.verifyUserCredentials("u1", "u1");

        Assert.assertTrue(userOptional.isPresent());
        Assert.assertEquals(new SOUser(1, "u1", "u1"), userOptional.get());
    }
}
