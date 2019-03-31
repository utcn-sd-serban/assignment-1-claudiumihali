package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Repository;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.RepositoryFactory;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "SD2019StackOverflowApp.repository-type", havingValue = "JDBC")
class JdbcRepositoryFactory implements RepositoryFactory {
    private final JdbcTemplate template;

    @Override
    public Repository<SOUser> createSOUserRepository() {
        return new JdbcRepository<>(template);
    }

    @Override
    public Repository<Question> createQuestionRepository() {
        return new JdbcRepository<>(template);
    }

    @Override
    public Repository<Tag> createTagRepository() {
        return new JdbcRepository<>(template);
    }

    @Override
    public Repository<QuestionTag> createQuestionTagRepository() {
        return new JdbcRepository<>(template);
    }

    @Override
    public Repository<QuestionVote> createQuestionVoteRepository() {
        return new JdbcRepository<>(template);
    }

    @Override
    public Repository<Answer> createAnswerRepository() {
        return new JdbcRepository<>(template);
    }

    @Override
    public Repository<AnswerVote> createAnswerVoteRepository() {
        return new JdbcRepository<>(template);
    }
}
