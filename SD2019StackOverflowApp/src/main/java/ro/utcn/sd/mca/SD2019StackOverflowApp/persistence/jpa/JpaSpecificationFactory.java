package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.SpecificationFactory;

@Component
@ConditionalOnProperty(name = "SD2019StackOverflowApp.repository-type", havingValue = "JPA")
class JpaSpecificationFactory implements SpecificationFactory {
    @Override
    public Specification<SOUser> createFindSOUserById(Integer sOUserId) {
        return new FindSOUserByIdJpa(sOUserId);
    }

    @Override
    public Specification<SOUser> createFindSOUserByUsername(String username) {
        return new FindSOUserByUsernameJpa(username);
    }

    @Override
    public Specification<SOUser> createVerifySOUserCredentials(String username, String password) {
        return new VerifySOUserCredentialsJpa(username, password);
    }

    @Override
    public Specification<Tag> createFindTagByText(String text) {
        return new FindTagByTextJpa(text);
    }

    @Override
    public Specification<Question> createFindQuestionById(Integer questionId) {
        return new FindQuestionByIdJpa(questionId);
    }

    @Override
    public Specification<QuestionVote> createFindSOUserQuestionVote(Integer sOUserId, Integer questionId) {
        return new FindSOUserQuestionVoteJpa(sOUserId, questionId);
    }

    @Override
    public Specification<QuestionVote> createFindQuestionVotes(Integer questionId, VoteType voteType) {
        return new FindQuestionVotesJpa(questionId, voteType);
    }

    @Override
    public Specification<Tag> createFindQuestionTags(Integer questionId) {
        return new FindQuestionTagsJpa(questionId);
    }

    @Override
    public Specification<Question> createFindAllQuestions() {
        return new FindAllQuestionsJpa();
    }

    @Override
    public Specification<Question> createFindQuestionByTitle(String title) {
        return new FindQuestionByTitleJpa(title);
    }

    @Override
    public Specification<Question> createFindQuestionByTagId(Integer tagId) {
        return new FindQuestionByTagIdJpa(tagId);
    }

    @Override
    public Specification<AnswerVote> createFindSOUserAnswerVote(Integer sOUserId, Integer answerId) {
        return new FindSOUserAnswerVoteJpa(sOUserId, answerId);
    }

    @Override
    public Specification<Answer> createFindAnswerById(Integer answerId) {
        return new FindAnswerByIdJpa(answerId);
    }

    @Override
    public Specification<Answer> createFindAnswerByQuestionId(Integer questionId) {
        return new FindAnswerByQuestionIdJpa(questionId);
    }

    @Override
    public Specification<AnswerVote> createFindAnswerVotes(Integer answerId, VoteType voteType) {
        return new FindAnswerVotesJpa(answerId, voteType);
    }
}
