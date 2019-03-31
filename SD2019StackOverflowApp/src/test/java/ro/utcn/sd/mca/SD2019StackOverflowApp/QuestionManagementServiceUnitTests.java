package ro.utcn.sd.mca.SD2019StackOverflowApp;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidQuestionIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidSOUserIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.RepositoryFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.SpecificationFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory.MemoryRepositoryFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory.MemorySpecificationFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.QuestionManagementService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class QuestionManagementServiceUnitTests {
    private static RepositoryFactory createMockedRepositoryFactory() {
        RepositoryFactory factory = new MemoryRepositoryFactory();

        factory.createSOUserRepository().save(new SOUser(1, "u1", "u1"));
        factory.createSOUserRepository().save(new SOUser(2, "u2", "u2"));
        factory.createSOUserRepository().save(new SOUser(3, "u3", "u3"));

        factory.createQuestionRepository().save(new Question(1, 1, "q1", "q1", LocalDateTime.now()));
        factory.createQuestionRepository().save(new Question(2, 2, "q2", "q2", LocalDateTime.now()));
        factory.createQuestionRepository().save(new Question(3, 2, "q3", "q3", LocalDateTime.now()));

        factory.createQuestionVoteRepository().save(new QuestionVote(1, 2, 1, VoteType.DOWNVOTE.getDatabaseText()));

        return factory;
    }

    private static SpecificationFactory createMockedSpecificationFactory() {
        return new MemorySpecificationFactory();
    }

    @Test
    public void testGetAllQuestions() {
        RepositoryFactory repositoryFactory = createMockedRepositoryFactory();
        SpecificationFactory specificationFactory = createMockedSpecificationFactory();
        QuestionManagementService questionService = new QuestionManagementService(repositoryFactory, specificationFactory);

        List<QuestionInfo> questionInfoList = questionService.getAllQuestions();

        Assert.assertEquals(3, questionInfoList.size());
    }

    @Test
    public void testDownvoteQuestionTwice() throws InvalidQuestionIdException, InvalidSOUserIdException {
        RepositoryFactory repositoryFactory = createMockedRepositoryFactory();
        SpecificationFactory specificationFactory = createMockedSpecificationFactory();
        QuestionManagementService questionService = new QuestionManagementService(repositoryFactory, specificationFactory);

        Optional<QuestionVote> questionVote = questionService.voteQuestion(2, 1, VoteType.DOWNVOTE);

        Assert.assertFalse(questionVote.isPresent());
    }
}
