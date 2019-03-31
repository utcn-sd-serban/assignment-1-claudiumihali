package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.AnswerVote;

class FindSOUserAnswerVoteMemory implements MemorySpecification<AnswerVote> {
    private Integer sOUserId;
    private Integer answerId;

    FindSOUserAnswerVoteMemory(Integer sOUserId, Integer answerId) {
        this.sOUserId = sOUserId;
        this.answerId = answerId;
    }

    @Override
    public boolean specified(AnswerVote answerVote) {
        return sOUserId.equals(answerVote.getSOUserId()) && answerId.equals(answerVote.getAnswerId());
    }
}
