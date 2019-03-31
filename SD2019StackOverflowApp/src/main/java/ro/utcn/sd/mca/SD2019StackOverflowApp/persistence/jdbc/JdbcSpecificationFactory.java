package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.SpecificationFactory;

@Component
@ConditionalOnProperty(name = "SD2019StackOverflowApp.repository-type", havingValue = "JDBC")
class JdbcSpecificationFactory implements SpecificationFactory {
    @Override
    public Specification<SOUser> createFindSOUserById(Integer sOUserId) {
        return new FindSOUserByIdJdbc(sOUserId);
    }

    @Override
    public Specification<SOUser> createFindSOUserByUsername(String username) {
        return new FindSOUserByUsernameJdbc(username);
    }

    @Override
    public Specification<SOUser> createVerifySOUserCredentials(String username, String password) {
        return new VerifySOUserCredentialsJdbc(username, password);
    }

    @Override
    public Specification<Tag> createFindTagByText(String text) {
        return new FindTagByTextJdbc(text);
    }

    @Override
    public Specification<Question> createFindQuestionById(Integer questionId) {
        return new FindQuestionByIdJdbc(questionId);
    }

    @Override
    public Specification<QuestionVote> createFindSOUserQuestionVote(Integer sOUserId, Integer questionId) {
        return new FindSOUserQuestionVoteJdbc(sOUserId, questionId);
    }

    @Override
    public Specification<QuestionVote> createFindQuestionVotes(Integer questionId, VoteType voteType) {
        return new FindQuestionVotesJdbc(questionId, voteType);
    }

    @Override
    public Specification<Tag> createFindQuestionTags(Integer questionId) {
        return new FindQuestionTagsJdbc(questionId);
    }

    @Override
    public Specification<Question> createFindAllQuestions() {
        return new FindAllQuestionsJdbc();
    }

    @Override
    public Specification<Question> createFindQuestionByTitle(String title) {
        return new FindQuestionByTitleJdbc(title);
    }

    @Override
    public Specification<Question> createFindQuestionByTagId(Integer tagId) {
        return new FindQuestionByTagIdJdbc(tagId);
    }

    @Override
    public Specification<AnswerVote> createFindSOUserAnswerVote(Integer sOUserId, Integer answerId) {
        return new FindSOUserAnswerVoteJdbc(sOUserId, answerId);
    }

    @Override
    public Specification<Answer> createFindAnswerById(Integer answerId) {
        return new FindAnswerByIdJdbc(answerId);
    }

    @Override
    public Specification<Answer> createFindAnswerByQuestionId(Integer questionId) {
        return new FindAnswerByQuestionIdJdbc(questionId);
    }

    @Override
    public Specification<AnswerVote> createFindAnswerVotes(Integer answerId, VoteType voteType) {
        return new FindAnswerVotesJdbc(answerId, voteType);
    }
}
