package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.SpecificationFactory;

@Component
@ConditionalOnProperty(name = "SD2019StackOverflowApp.repository-type", havingValue = "MEMORY")
public class MemorySpecificationFactory implements SpecificationFactory {
    @Override
    public Specification<SOUser> createFindSOUserById(Integer sOUserId) {
        return new FindSOUserByIdMemory(sOUserId);
    }

    @Override
    public Specification<SOUser> createFindSOUserByUsername(String username) {
        return new FindSOUserByUsernameMemory(username);
    }

    @Override
    public Specification<SOUser> createVerifySOUserCredentials(String username, String password) {
        return new VerifySOUserCredentialsMemory(username, password);
    }

    @Override
    public Specification<Tag> createFindTagByText(String text) {
        return new FindTagByTextMemory(text);
    }

    @Override
    public Specification<Question> createFindQuestionById(Integer questionId) {
        return new FindQuestionByIdMemory(questionId);
    }

    @Override
    public Specification<QuestionVote> createFindSOUserQuestionVote(Integer sOUserId, Integer questionId) {
        return new FindSOUserQuestionVoteMemory(sOUserId, questionId);
    }

    @Override
    public Specification<QuestionVote> createFindQuestionVotes(Integer questionId, VoteType voteType) {
        return new FindQuestionVotesMemory(questionId, voteType);
    }

    @Override
    public Specification<Tag> createFindQuestionTags(Integer questionId) {
        return new FindQuestionTagsMemory(questionId);
    }

    @Override
    public Specification<Question> createFindAllQuestions() {
        return new FindAllQuestionsMemory();
    }

    @Override
    public Specification<Question> createFindQuestionByTitle(String title) {
        return new FindQuestionByTitleMemory(title);
    }

    @Override
    public Specification<Question> createFindQuestionByTagId(Integer tagId) {
        return new FindQuestionByTagIdMemory(tagId);
    }

    @Override
    public Specification<AnswerVote> createFindSOUserAnswerVote(Integer sOUserId, Integer answerId) {
        return new FindSOUserAnswerVoteMemory(sOUserId, answerId);
    }

    @Override
    public Specification<Answer> createFindAnswerById(Integer answerId) {
        return new FindAnswerByIdMemory(answerId);
    }

    @Override
    public Specification<Answer> createFindAnswerByQuestionId(Integer questionId) {
        return new FindAnswerByQuestionIdMemory(questionId);
    }

    @Override
    public Specification<AnswerVote> createFindAnswerVotes(Integer answerId, VoteType voteType) {
        return new FindAnswerVotesMemory(answerId, voteType);
    }
}
