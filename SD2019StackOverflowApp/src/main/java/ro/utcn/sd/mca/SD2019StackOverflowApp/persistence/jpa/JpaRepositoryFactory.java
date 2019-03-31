package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Repository;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.RepositoryFactory;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "SD2019StackOverflowApp.repository-type", havingValue = "JPA")
class JpaRepositoryFactory implements RepositoryFactory {
    private final EntityManager entityManager;

    @Override
    public Repository<SOUser> createSOUserRepository() {
        return new JpaRepository<>(entityManager);
    }

    @Override
    public Repository<Question> createQuestionRepository() {
        return new JpaRepository<>(entityManager);
    }

    @Override
    public Repository<Tag> createTagRepository() {
        return new JpaRepository<>(entityManager);
    }

    @Override
    public Repository<QuestionTag> createQuestionTagRepository() {
        return new JpaRepository<>(entityManager);
    }

    @Override
    public Repository<QuestionVote> createQuestionVoteRepository() {
        return new JpaRepository<>(entityManager);
    }

    @Override
    public Repository<Answer> createAnswerRepository() {
        return new JpaRepository<>(entityManager);
    }

    @Override
    public Repository<AnswerVote> createAnswerVoteRepository() {
        return new JpaRepository<>(entityManager);
    }
}
