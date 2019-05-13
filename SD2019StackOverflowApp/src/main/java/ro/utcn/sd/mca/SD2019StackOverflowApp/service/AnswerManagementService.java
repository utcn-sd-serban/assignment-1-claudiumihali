package ro.utcn.sd.mca.SD2019StackOverflowApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidAnswerIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidQuestionIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidSOUserIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.RepositoryFactory;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.Specification;
import ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.SpecificationFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerManagementService {
    private final RepositoryFactory repositoryFactory;
    private final SpecificationFactory specificationFactory;

    @Transactional
    public Answer addAnswer(Integer questionId, Integer sOUserId, String text) throws InvalidQuestionIdException, InvalidSOUserIdException {
        Specification<Question> sq = specificationFactory.createFindQuestionById(questionId);
        List<Question> lq = repositoryFactory.createQuestionRepository().query(sq);
        if (lq.isEmpty())
            throw new InvalidQuestionIdException();

        Specification<SOUser> su = specificationFactory.createFindSOUserById(sOUserId);
        List<SOUser> lu = repositoryFactory.createSOUserRepository().query(su);
        if (lu.isEmpty())
            throw new InvalidSOUserIdException();

        LocalDateTime creationDateTime = LocalDateTime.now();

        return repositoryFactory.createAnswerRepository().save(new Answer(null, questionId, sOUserId, text, creationDateTime));
    }

    @Transactional
    public Optional<AnswerVote> voteAnswer(Integer sOUserId, Integer answerId, VoteType voteType) throws InvalidSOUserIdException, InvalidAnswerIdException {
        Specification<SOUser> su = specificationFactory.createFindSOUserById(sOUserId);
        List<SOUser> lu = repositoryFactory.createSOUserRepository().query(su);
        if (lu.isEmpty())
            throw new InvalidSOUserIdException();

        Specification<Answer> sa = specificationFactory.createFindAnswerById(answerId);
        List<Answer> la = repositoryFactory.createAnswerRepository().query(sa);
        if (la.isEmpty())
            throw new InvalidAnswerIdException();

        if (sOUserId.equals(la.get(0).getSOUserId()))
            return Optional.empty();

        Specification<AnswerVote> sav = specificationFactory.createFindSOUserAnswerVote(sOUserId, answerId);
        List<AnswerVote> lav = repositoryFactory.createAnswerVoteRepository().query(sav);
        if (lav.isEmpty()) {
            return Optional.of(repositoryFactory.createAnswerVoteRepository().save(new AnswerVote(null, sOUserId, answerId,
                    voteType.getDatabaseText())));
        } else {
            String answerVote = lav.get(0).getVoteType();
            if (answerVote.equals(voteType.getDatabaseText())) {
                return Optional.empty();
            } else {
                return Optional.of(repositoryFactory.createAnswerVoteRepository().save(new AnswerVote(lav.get(0).getId(), sOUserId, answerId,
                        voteType.getDatabaseText())));
            }
        }
    }

    @Transactional
    public List<AnswerInfo> getQuestionAnswers(Integer questionId) throws InvalidQuestionIdException {
        List<AnswerInfo> ail = new ArrayList<>();

        Specification<Question> sq = specificationFactory.createFindQuestionById(questionId);
        List<Question> lq = repositoryFactory.createQuestionRepository().query(sq);
        if (lq.isEmpty())
            throw new InvalidQuestionIdException();

        Specification<Answer> sa = specificationFactory.createFindAnswerByQuestionId(questionId);
        List<Answer> la = repositoryFactory.createAnswerRepository().query(sa);

        Specification<AnswerVote> sav;
        for (Answer a : la) {
            sav = specificationFactory.createFindAnswerVotes(a.getId(), VoteType.UPVOTE);
            List<AnswerVote> lu = repositoryFactory.createAnswerVoteRepository().query(sav);

            sav = specificationFactory.createFindAnswerVotes(a.getId(), VoteType.DOWNVOTE);
            List<AnswerVote> ld = repositoryFactory.createAnswerVoteRepository().query(sav);

            Integer votes = lu.size() - ld.size();

            ail.add(new AnswerInfo(a, votes));
        }

        ail.sort((ai1, ai2) -> ai2.getVotes() - ai1.getVotes());

        return ail;
    }

    @Transactional
    public Optional<Answer> editAnswer(Integer sOUserId, Integer answerId, String newAnswerText) throws InvalidSOUserIdException, InvalidAnswerIdException {
        Specification<SOUser> su = specificationFactory.createFindSOUserById(sOUserId);
        List<SOUser> lu = repositoryFactory.createSOUserRepository().query(su);
        if (lu.isEmpty())
            throw new InvalidSOUserIdException();

        Specification<Answer> sa = specificationFactory.createFindAnswerById(answerId);
        List<Answer> la = repositoryFactory.createAnswerRepository().query(sa);
        if (la.isEmpty())
            throw new InvalidAnswerIdException();

        if (!sOUserId.equals(la.get(0).getSOUserId())) {
            return Optional.empty();
        } else {
            repositoryFactory.createAnswerRepository().save(new Answer(answerId, null, null, newAnswerText, null));
            return Optional.of(repositoryFactory.createAnswerRepository().query(sa).get(0));
        }
    }

    @Transactional
    public boolean deleteAnswer(Integer sOUserId, Integer answerId) throws InvalidSOUserIdException, InvalidAnswerIdException {
        Specification<SOUser> su = specificationFactory.createFindSOUserById(sOUserId);
        List<SOUser> lu = repositoryFactory.createSOUserRepository().query(su);
        if (lu.isEmpty())
            throw new InvalidSOUserIdException();

        Specification<Answer> sa = specificationFactory.createFindAnswerById(answerId);
        List<Answer> la = repositoryFactory.createAnswerRepository().query(sa);
        if (la.isEmpty())
            throw new InvalidAnswerIdException();

        if (!sOUserId.equals(la.get(0).getSOUserId())) {
            return false;
        } else {
            repositoryFactory.createAnswerRepository().remove(new Answer(answerId, null, null, null, null));
            return true;
        }
    }
}
