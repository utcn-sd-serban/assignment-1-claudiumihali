package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionTag;

class FindQuestionTagByQuestionIdMemory implements MemorySpecification<QuestionTag> {
    private Integer questionId;

    FindQuestionTagByQuestionIdMemory(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean specified(QuestionTag questionTag) {
        return questionId.equals(questionTag.getQuestionId());
    }
}
