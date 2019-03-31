package ro.utcn.sd.mca.SD2019StackOverflowApp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidAnswerIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidQuestionIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidSOUserIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.RepositoryFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.SpecificationFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.AnswerManagementService;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.QuestionManagementService;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.SOUserManagementService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IntegrationTests {
    @Autowired
    private RepositoryFactory repositoryFactory;
    @Autowired
    private SpecificationFactory specificationFactory;
    @Autowired
    private SOUserManagementService sOUserService;
    @Autowired
    private QuestionManagementService questionService;
    @Autowired
    private AnswerManagementService answerService;

    // instantiaza de 2 ori clasa asta nush de ce
    private static boolean firstSeed = true;

    @Before
    public void seed() {
        if (firstSeed) {
            firstSeed = false;

            repositoryFactory.createSOUserRepository().save(new SOUser(null, "u1", "u1"));
            repositoryFactory.createSOUserRepository().save(new SOUser(null, "u2", "u2"));
            repositoryFactory.createSOUserRepository().save(new SOUser(null, "u3", "u3"));

            repositoryFactory.createQuestionRepository().save(new Question(null, 1, "q1", "q1", LocalDateTime.now()));
            repositoryFactory.createQuestionRepository().save(new Question(null, 2, "q2", "q2", LocalDateTime.now()));
            repositoryFactory.createQuestionRepository().save(new Question(null, 2, "q3", "q3", LocalDateTime.now()));

            repositoryFactory.createAnswerRepository().save(new Answer(null, 2, 1, "a1", LocalDateTime.now()));

            repositoryFactory.createQuestionVoteRepository().save(new QuestionVote(null, 2, 1, VoteType.DOWNVOTE.getDatabaseText()));
        }
    }

    @Test
    public void testLogIn() {
        Optional<SOUser> userOptional = sOUserService.verifyUserCredentials("u1", "u1");

        Assert.assertTrue(userOptional.isPresent());
        Assert.assertEquals(new SOUser(1, "u1", "u1"), userOptional.get());
    }

    @Test
    public void testAddQuestion() {
        List<QuestionInfo> qil = questionService.getAllQuestions();

        Assert.assertEquals(3, qil.size());
    }

    @Test
    public void testAddAnswer() throws InvalidQuestionIdException {
        List<AnswerInfo> ail = answerService.getQuestionAnswers(2);

        Assert.assertEquals(1, ail.size());
    }

    @Test(expected = InvalidQuestionIdException.class)
    public void testAddAnswerInvalidQuestionId() throws InvalidQuestionIdException {
        List<AnswerInfo> ail = answerService.getQuestionAnswers(999);
    }

    @Test
    public void testDownvoteQuestionTwice() throws InvalidQuestionIdException, InvalidSOUserIdException {
        Optional<QuestionVote> questionVote = questionService.voteQuestion(2, 1, VoteType.DOWNVOTE);

        Assert.assertFalse(questionVote.isPresent());
    }

    @Test
    public void testEditAnswer() throws InvalidAnswerIdException, InvalidSOUserIdException {
        Optional<Answer> answer = answerService.editAnswer(1, 1, "new answer text");

        Assert.assertTrue(answer.isPresent());
        Assert.assertEquals("new answer text", answer.get().getText());
    }

    @Test
    public void testEditAnswerByNonAuthor() throws InvalidAnswerIdException, InvalidSOUserIdException {
        Optional<Answer> answer = answerService.editAnswer(2, 1, "new answer text");

        Assert.assertFalse(answer.isPresent());
    }
}
