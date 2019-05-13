package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Repository;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.RepositoryFactory;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "SD2019StackOverflowApp.repository-type", havingValue = "MEMORY")
public class MemoryRepositoryFactory implements RepositoryFactory {
    private final MemoryRepository<SOUser> sOUserRepository = new MemoryRepository<>();
    private final MemoryRepository<Question> questionRepository = new MemoryRepository<>();
    private final MemoryRepository<Tag> tagRepository = new MemoryRepository<>();
    static final MemoryRepository<QuestionTag> questionTagRepository = new MemoryRepository<>();
    private final MemoryRepository<QuestionVote> questionVoteRepository = new MemoryRepository<>();
    private final MemoryRepository<Answer> answerRepository = new MemoryRepository<>();
    private final MemoryRepository<AnswerVote> answerVoteRepository = new MemoryRepository<>();

    @Override
    public Repository<SOUser> createSOUserRepository() {
        return sOUserRepository;
    }

    @Override
    public Repository<Question> createQuestionRepository() {
        return questionRepository;
    }

    @Override
    public Repository<Tag> createTagRepository() {
        return tagRepository;
    }

    @Override
    public Repository<QuestionTag> createQuestionTagRepository() {
        return questionTagRepository;
    }

    @Override
    public Repository<QuestionVote> createQuestionVoteRepository() {
        return questionVoteRepository;
    }

    @Override
    public Repository<Answer> createAnswerRepository() {
        return answerRepository;
    }

    @Override
    public Repository<AnswerVote> createAnswerVoteRepository() {
        return answerVoteRepository;
    }
}
