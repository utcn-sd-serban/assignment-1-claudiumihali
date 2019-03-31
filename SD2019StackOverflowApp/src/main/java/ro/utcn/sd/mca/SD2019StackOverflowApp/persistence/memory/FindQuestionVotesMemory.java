package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionVote;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.VoteType;

class FindQuestionVotesMemory implements MemorySpecification<QuestionVote> {
    private Integer questionId;
    private VoteType voteType;

    FindQuestionVotesMemory(Integer questionId, VoteType voteType) {
        this.questionId = questionId;
        this.voteType = voteType;
    }

    @Override
    public boolean specified(QuestionVote questionVote) {
        return questionId.equals(questionVote.getQuestionId()) && voteType.getDatabaseText().equals(questionVote.getVoteType());
    }
}
