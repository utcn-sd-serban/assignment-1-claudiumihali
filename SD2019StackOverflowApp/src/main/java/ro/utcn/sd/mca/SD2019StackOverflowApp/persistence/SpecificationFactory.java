package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;

public interface SpecificationFactory {
    Specification<SOUser> createFindSOUserById(Integer sOUserId);
    Specification<SOUser> createFindSOUserByUsername(String username);
    Specification<SOUser> createVerifySOUserCredentials(String username, String password);
    Specification<Tag> createFindTagByText(String text);
    Specification<Question> createFindQuestionById(Integer questionId);
    Specification<QuestionVote> createFindSOUserQuestionVote(Integer sOUserId, Integer questionId);
    Specification<QuestionVote> createFindQuestionVotes(Integer questionId, VoteType voteType);
    Specification<Tag> createFindQuestionTags(Integer questionId);
    Specification<Question> createFindAllQuestions();
    Specification<Question> createFindQuestionByTitle(String title);
    Specification<Question> createFindQuestionByTagId(Integer tagId);
    Specification<AnswerVote> createFindSOUserAnswerVote(Integer sOUserId, Integer answerId);
    Specification<Answer> createFindAnswerById(Integer answerId);
    Specification<Answer> createFindAnswerByQuestionId(Integer questionId);
    Specification<AnswerVote> createFindAnswerVotes(Integer answerId, VoteType voteType);
}
