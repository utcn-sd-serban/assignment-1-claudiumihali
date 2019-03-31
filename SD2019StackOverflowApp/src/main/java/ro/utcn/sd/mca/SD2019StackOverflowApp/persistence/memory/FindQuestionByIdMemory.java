package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;

class FindQuestionByIdMemory implements MemorySpecification<Question> {
    private Integer questionId;

    FindQuestionByIdMemory(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean specified(Question question) {
        return questionId.equals(question.getId());
    }
}
