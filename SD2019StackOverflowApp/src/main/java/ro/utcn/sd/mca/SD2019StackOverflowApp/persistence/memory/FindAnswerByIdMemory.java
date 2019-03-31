package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Answer;

class FindAnswerByIdMemory implements MemorySpecification<Answer> {
    private Integer answerId;

    FindAnswerByIdMemory(Integer answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean specified(Answer answer) {
        return answerId.equals(answer.getId());
    }
}
