package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;

public interface RepositoryFactory {
    Repository<SOUser> createSOUserRepository();
    Repository<Question> createQuestionRepository();
    Repository<Tag> createTagRepository();
    Repository<QuestionTag> createQuestionTagRepository();
    Repository<QuestionVote> createQuestionVoteRepository();
    Repository<Answer> createAnswerRepository();
    Repository<AnswerVote> createAnswerVoteRepository();
}
