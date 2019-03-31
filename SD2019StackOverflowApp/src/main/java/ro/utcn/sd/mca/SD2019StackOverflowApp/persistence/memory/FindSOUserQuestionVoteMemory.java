package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionVote;

class FindSOUserQuestionVoteMemory implements MemorySpecification<QuestionVote> {
    private Integer sOUserId;
    private Integer questionId;

    FindSOUserQuestionVoteMemory(Integer sOUserId, Integer questionId) {
        this.sOUserId = sOUserId;
        this.questionId = questionId;
    }

    @Override
    public boolean specified(QuestionVote questionVote) {
        return sOUserId.equals(questionVote.getSOUserId()) && questionId.equals(questionVote.getQuestionId());
    }
}
