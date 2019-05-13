package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Answer;

class FindAnswerByQuestionIdMemory implements MemorySpecification<Answer> {
    private Integer questionId;

    FindAnswerByQuestionIdMemory(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean specified(Answer answer) {
        return questionId.equals(answer.getQuestionId());
    }
}
