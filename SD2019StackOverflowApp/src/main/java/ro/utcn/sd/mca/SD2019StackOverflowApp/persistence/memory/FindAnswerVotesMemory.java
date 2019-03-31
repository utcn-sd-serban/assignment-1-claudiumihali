package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.AnswerVote;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.VoteType;

class FindAnswerVotesMemory implements MemorySpecification<AnswerVote> {
    private Integer answerId;
    private VoteType voteType;

    FindAnswerVotesMemory(Integer answerId, VoteType voteType) {
        this.answerId = answerId;
        this.voteType = voteType;
    }

    @Override
    public boolean specified(AnswerVote answerVote) {
        return answerId.equals(answerVote.getAnswerId()) && voteType.getDatabaseText().equals(answerVote.getVoteType());
    }
}
